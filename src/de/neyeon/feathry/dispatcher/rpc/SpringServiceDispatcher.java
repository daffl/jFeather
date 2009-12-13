package de.neyeon.feathry.dispatcher.rpc;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import de.neyeon.feathry.dispatcher.Interceptable;

/**
 * A centralized Service registry where all method calls will be redirected to.
 * Uses the injected {@link ApplicationContext} for service object retrieval.
 * @author daff
 *
 */
public class SpringServiceDispatcher implements ApplicationContextAware, ServiceDispatcher
{
	private String namingPattern;
	protected ApplicationContext context;
	protected Logger log = Logger.getLogger(this.getClass());
	
	/* (non-Javadoc)
	 * @see de.neyeon.feathry.dispatcher.rpc.ServiceDispatcher#invoke(de.neyeon.feathry.dispatcher.rpc.RemoteProcedureCall)
	 */
	public Object invoke(RemoteProcedureCall rpc) throws Throwable
	{
		log.debug("Invoking RPC " + rpc);
		Object service = this.getService(rpc.getServiceName());
		try
		{
			Method toDispatch = rpc.getDispatchableMethod(service);
			return toDispatch.invoke(service, rpc.getArguments());
		} catch (NoSuchMethodException e)
		{
			if(service instanceof Interceptable)
			{
				log.debug("Calling invoke(String, Object[]) on Interceptable service");
				return ((Interceptable)service).invoke(rpc.getMethodName(), rpc.getArguments());
			}
			else
				throw e;
		}
	}
	
	public Object getService(String serviceName)
	{
		// TODO use a service naming pattern
		return context.getBean(serviceName);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException
	{
		context = applicationContext;
	}

	public void setNamingPattern(String namingPattern)
	{
		this.namingPattern = namingPattern;
	}

	public String getNamingPattern()
	{
		return namingPattern;
	}
}
