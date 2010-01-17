package de.neyeon.feathry.dispatcher.rmi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.remoting.rmi.RmiServiceExporter;

import de.neyeon.feathry.dispatcher.rpc.ServiceRegistry;

public class ProxiedRmiExporter extends RmiServiceExporter
{
	private Object proxy;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	public ProxiedRmiExporter(String serviceName, ServiceRegistry registry)
	{
		super();
		Class<?> serviceInterface = registry.getServiceInterface(serviceName);
		this.setServiceInterface(serviceInterface);
		this.setServiceName(serviceName);
		InterceptorProxy interceptor = new InterceptorProxy(serviceName, registry);		
		proxy = interceptor.getObject();
		this.setService(proxy);
		log.debug("Exported service {}, with {} via RMI", serviceName, serviceInterface);
	}
	
	public Object getObject()
	{
		return proxy;
	}
}