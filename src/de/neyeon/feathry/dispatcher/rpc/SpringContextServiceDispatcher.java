package de.neyeon.feathry.dispatcher.rpc;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import de.neyeon.feathry.dispatcher.Interceptable;

/**
 * A centralized Service registry where all method calls will be redirected to.
 * Uses the injected {@link ApplicationContext} for service object retrieval.
 * @author daff
 */
public class SpringContextServiceDispatcher implements ApplicationContextAware, ServiceDispatcher
{
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private String namingPostfix;
	protected ApplicationContext context;
	protected List<String> serviceNames;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.neyeon.feathry.dispatcher.rpc.ServiceDispatcher#invoke(de.neyeon.feathry
	 * .dispatcher.rpc.RemoteProcedureCall)
	 */
	public Object invoke(RemoteProcedureCall rpc) throws Throwable
	{
		// TODO refactor this
		Object service = this.getService(rpc.getServiceName());
		ServiceInvocationHandler handler = new ServiceInvocationHandler(service);
		return handler.invoke(rpc);
	}

	@Override
	public Object getService(String serviceName)
	{
		String name = serviceName + namingPostfix;
		return context.getBean(name);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
	{
		context = applicationContext;
		serviceNames = new ArrayList<String>();
		// TODO find a good naming schema here (especially capitalization of first letters)
		for(String curname : applicationContext.getBeanDefinitionNames())
		{
			// Only add the service name without postfix
			if(curname.endsWith(namingPostfix))
			{
				serviceNames.add(curname.substring(0, curname.indexOf(namingPostfix)));
			}
		}
	}

	@Override
	public List<String> getServiceNames()
	{
		return serviceNames;
	}

	public void setNamingPostfix(String namingPostfix)
	{
		this.namingPostfix = namingPostfix;
	}

	/**
	 * Set the service naming postfix
	 * @return
	 */
	public String getNamingPostfix()
	{
		return namingPostfix;
	}
}
