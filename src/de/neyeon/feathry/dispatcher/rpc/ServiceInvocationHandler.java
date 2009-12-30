package de.neyeon.feathry.dispatcher.rpc;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.DynaBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.neyeon.feathry.beans.ChoiceException;
import de.neyeon.feathry.beans.ExtendedDynaBean;
import de.neyeon.feathry.dispatcher.Interceptable;

public class ServiceInvocationHandler
{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final Class<?> serviceClass;
	private final Object serviceInstance;

	protected class MethodRpcTuple
	{
		private final Method method;
		private final RemoteProcedureCall remoteProcedureCall;

		public MethodRpcTuple(Method method, RemoteProcedureCall remoteProcedureCall)
		{
			this.method = method;
			this.remoteProcedureCall = remoteProcedureCall;
		}

		/**
		 * @return the method
		 */
		public Method getMethod()
		{
			return method;
		}

		/**
		 * @return the remoteProcedureCall
		 */
		public RemoteProcedureCall getRemoteProcedureCall()
		{
			return remoteProcedureCall;
		}
	}

	public ServiceInvocationHandler(Object serviceInstance)
	{
		this(serviceInstance.getClass(), serviceInstance);
	}

	public ServiceInvocationHandler(Class<?> serviceClass, Object serviceInstance)
	{
		if (!serviceClass.isInstance(serviceInstance))
		{
			throw new IllegalArgumentException("Instance of type "
					+ serviceInstance.getClass().getName() + " is not an instance of "
					+ serviceClass.getName());
		}
		this.serviceClass = serviceClass;
		this.serviceInstance = serviceInstance;
	}

	public Object invoke(RemoteProcedureCall rpc) throws Throwable
	{
		log.debug("Invoking RPC {}", rpc);
		try
		{
			Method method = serviceClass.getMethod(rpc.getMethodName(), rpc.getParameterTypes());
			log.debug("Found method {} in service class {}", rpc.getMethodName(), serviceClass
					.getName());
			return method.invoke(serviceInstance, rpc.getArguments());
		} catch (NoSuchMethodException e)
		{
			// Method not found, try searching for it
			List<Method> methods = this.getMethods(rpc.getMethodName(), rpc.getParameterCount());
			if (methods.size() == 0)
			{
				if(serviceInstance instanceof Interceptable)
				{
					log.debug("Calling invoke on interceptable service instance");
					return ((Interceptable) serviceInstance).invoke(rpc.getMethodName(), rpc
							.getArguments());
				}
				else
				{
					throw new NoSuchMethodException("Could not find any matching method for method " + rpc.getMethodName());
				}
			}
			// TODO only use Interceptable as a last resort
			MethodRpcTuple tuple = this.findClosest(methods, rpc);
			Method method = tuple.getMethod();
			RemoteProcedureCall newRpc = tuple.getRemoteProcedureCall();
			return method.invoke(serviceInstance, newRpc.getArguments());
		}
	}

	@SuppressWarnings("unchecked")
	protected MethodRpcTuple findClosest(List<Method> methods, RemoteProcedureCall rpc)
			throws NoSuchMethodException, ChoiceException
	{
		if (methods.size() == 0)
		{
			throw new NoSuchMethodException("Could not find any matching method " + rpc.getMethodName() + " with "
					+ rpc.getParameterCount() + " parameter(s). The list of methods coices is empty.");
		}
		Object[] arguments = rpc.getArguments();
		Object[] newArgs = new Object[arguments.length];
		for (int index = 0; index < arguments.length; index++)
		{
			Object arg = arguments[index];
			ExtendedDynaBean bean = null;
			if (arg instanceof Map)
			{
				bean = new ExtendedDynaBean(((Map) arg));
			} else if (arg instanceof DynaBean)
			{
				bean = new ExtendedDynaBean(bean);
			}
			if (bean != null)
			{
				Class<?>[] classlist = this.getTypesAt(methods, index);
				Class<?> newclass = bean.choose(classlist);
				log.debug("Created new DynaBean. Best matching class found is {}, creating object instance.", newclass);
				try
				{
					Object newarg = bean.getBean(newclass);
					newArgs[index] = newarg;
				} catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else
			{
				newArgs[index] = arguments[index];
			}
		}
		// Create the tuple
		RemoteProcedureCall resultRpc = new RemoteProcedureCall(rpc.getServiceName(), rpc
				.getMethodName(), newArgs);
		Method resultMethod = serviceClass.getMethod(resultRpc.getMethodName(), resultRpc.getParameterTypes());
		return new MethodRpcTuple(resultMethod, resultRpc);
	}

	/**
	 * @param methods
	 * @param index
	 * @return
	 */
	protected Class<?>[] getTypesAt(List<Method> methods, int index)
	{
		Class<?>[] result = new Class<?>[methods.size()];
		for (int i = 0; i < methods.size(); i++)
		{
			Class<?>[] paramtypes = methods.get(i).getParameterTypes();
			result[i] = paramtypes[index];
		}
		return result;
	}

	/**
	 * Returns all methods in serviceClass that have a given name and number of
	 * parameters.
	 * @param name The name of the methods to look for
	 * @param parameterCount The number of parameters
	 * @return A list of methods from serviceClass that match the given name and
	 *         parameter count
	 */
	protected List<Method> getMethods(String name, int parameterCount)
	{
		List<Method> result = new ArrayList<Method>();
		for (Method cur : serviceClass.getMethods())
		{
			if (cur.getName().equals(name) && cur.getParameterTypes().length == parameterCount)
			{
				result.add(cur);
			}
		}
		if (log.isDebugEnabled())
		{
			Object[] arg = { result.size(), name, parameterCount };
			log.debug("Found {} methods with name '{}' and {} parameters", arg);
		}
		return result;
	}

	/**
	 * @return the serviceClass
	 */
	public Class<?> getServiceClass()
	{
		return serviceClass;
	}

	/**
	 * @return the serviceInstance
	 */
	public Object getServiceInstance()
	{
		return serviceClass.cast(serviceInstance);
	}

}
