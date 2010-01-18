package de.neyeon.feathry.dispatcher.rpc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * A centralized Service registry where all method calls will be redirected to.
 * Uses the injected {@link ApplicationContext} for service object retrieval.
 * @author daff
 */
public class SpringContextRegistry implements ApplicationContextAware, ServiceRegistry
{
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private String namingPostfix;
	protected ApplicationContext context;
	protected List<String> serviceNames;
	protected Map<String, Class<?>> exportedInterfaces = new HashMap<String, Class<?>>();
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.neyeon.feathry.dispatcher.rpc.ServiceDispatcher#invoke(de.neyeon.feathry
	 * .dispatcher.rpc.RemoteProcedureCall)
	 */
	public Object invoke(RemoteProcedureCall rpc) throws Throwable
	{
		Object service = this.getService(rpc.getServiceName());
		ServiceDispatcher handler = new ServiceDispatcher(service);
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

	@Override
	public Class<?> getServiceInterface(String serviceName)
	{
		return exportedInterfaces.get(serviceName);
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

	@Override
	public void attachInterface(String serviceName, Class<?> serviceInterface)
	{
		if(!serviceInterface.isInterface())
		{
			throw new IllegalArgumentException("The given class	" + serviceInterface.getName() + " is not an interface.");
		}
		exportedInterfaces.put(serviceName, serviceInterface);
	}
	
	public void setInterfaces(Map<String, Class<?>> mapping)
	{
		log.debug("Setting mapping to {}", mapping);
		exportedInterfaces = mapping;
	}

	@Override
	public Object getServiceProxy(String serviceName)
	{
		ProxyFactoryBean bean = new ProxyFactoryBean();
		bean.addAdvice(new InterceptorAdvice(serviceName, this));
		Class<?> interfaceClass = this.getServiceInterface(serviceName);
		bean.addInterface(interfaceClass);
		return interfaceClass.cast(bean.getObject());
	}
}
