package org.feathry.dispatcher.rmi;

import org.feathry.dispatcher.rpc.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.remoting.rmi.RmiServiceExporter;


public class ProxiedRmiExporter extends RmiServiceExporter
{
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	public ProxiedRmiExporter(String serviceName, ServiceRegistry registry)
	{
		super();
		Class<?> serviceInterface = registry.getServiceInterface(serviceName);
		this.setServiceInterface(serviceInterface);
		this.setServiceName(serviceName);
		this.setService(registry.getServiceProxy(serviceName));
		log.debug("Exported service {}, with {} via RMI", serviceName, serviceInterface);
	}
}
