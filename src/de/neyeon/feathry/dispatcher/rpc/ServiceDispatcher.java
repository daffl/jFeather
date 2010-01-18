package de.neyeon.feathry.dispatcher.rpc;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.DynaBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.neyeon.feathry.beans.ExtendedDynaBean;
import de.neyeon.feathry.service.Interceptable;

/**
 * Class that maps a given service interface class to a service object. The
 * service object must implement the given service interface class. If no
 * service interface class has been provided the service object class will be
 * used, allowing to invoke any method on it. <br />
 * This class is responsible for invoking a {@link RemoteProcedureCall} on the
 * given service object.
 * @author David Luecke (daff@neyeon.de)
 */
public class ServiceDispatcher
{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final Class<?> serviceClass;
	private final Object serviceInstance;

	/**
	 * Create a new invocation handler with a given service object. The objects
	 * class will be used to determine the methods exported to the service
	 * dispatcher.
	 * @param serviceInstance The service instance to use
	 */
	public ServiceDispatcher(Object serviceInstance)
	{
		this(serviceInstance.getClass(), serviceInstance);
	}

	public ServiceDispatcher(Class<?> serviceClass, Object serviceInstance)
	{
		if (!serviceClass.isInstance(serviceInstance))
		{
			throw new ClassCastException("Instance of type " + serviceInstance.getClass().getName()
					+ " is not an instance of " + serviceClass.getName());
		}
		this.serviceClass = serviceClass;
		this.serviceInstance = serviceInstance;
	}

	/**
	 * @param rpc
	 * @return
	 * @throws Throwable
	 */
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
			return this.handleDynamicCall(rpc);
		}
	}

	/**
	 * This method will be called if no directly matching method could be found.
	 * @param rpc
	 * @return
	 * @throws Throwable
	 */
	protected Object handleDynamicCall(RemoteProcedureCall rpc) throws Throwable
	{
		// Method not found, try searching for it
		List<Method> methods = this.getMethods(rpc.getMethodName(), rpc.getParameterCount());
		if (methods.size() == 0)
		{
			return this.handleInterceptable(rpc);
		}
		Object[] arguments = rpc.getArguments();
		Object[] newArgs = new Object[arguments.length];
		for (int index = 0; index < arguments.length; index++)
		{
			Object arg = arguments[index];
			newArgs[index] = this.getParameter(arg, this.getTypesAt(methods, index));
		}
		RemoteProcedureCall newRpc = new RemoteProcedureCall(rpc.getServiceName(), rpc
				.getMethodName(), newArgs);
		try
		{
			Method method = serviceClass.getMethod(newRpc.getMethodName(), newRpc
					.getParameterTypes());
			return method.invoke(serviceInstance, newRpc.getArguments());
		} catch (NoSuchMethodException e)
		{
			// Handle the original rpc
			return this.handleInterceptable(rpc);
		}
	}

	protected Object handleInterceptable(RemoteProcedureCall rpc) throws NoSuchMethodException
	{
		if (serviceInstance instanceof Interceptable)
		{
			log.debug("Calling invoke on interceptable service instance");
			return ((Interceptable) serviceInstance)
					.invoke(rpc.getMethodName(), rpc.getArguments());
		} else
		{
			throw new NoSuchMethodException("Could not find any matching method for "
					+ rpc.getMethodName());
		}
	}

	protected Object getParameter(Object arg, Class<?>[] classlist) throws Throwable
	{
		ExtendedDynaBean bean = null;
		if (arg instanceof Map<?, ?>)
		{
			bean = new ExtendedDynaBean(((Map<?, ?>) arg));
		} else if (arg instanceof DynaBean)
		{
			bean = new ExtendedDynaBean(((DynaBean) bean));
		}
		if (bean != null)
		{
			Class<?> newclass = bean.choose(classlist);
			log.debug("Created new DynaBean. Best matching bean class found is {}", newclass);
			Object newarg = bean.getBean(newclass);
			return newarg;
		} else
		{
			return arg;
		}
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
		for (Method cur : getServiceClass().getMethods())
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
		return getServiceClass().cast(serviceInstance);
	}

}
