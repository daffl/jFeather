package de.neyeon.feathry.dispatcher.rmi;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import de.neyeon.feathry.dispatcher.rpc.ServiceRegistry;

public class RmiRegistry implements InitializingBean, DisposableBean
{
	private Logger log = LoggerFactory.getLogger(this.getClass());
	protected final ServiceRegistry registry;
	protected Map<String, ProxiedRmiExporter> exporters;
	private int port;
	
	public RmiRegistry(ServiceRegistry registry) throws RemoteException
	{
		this.registry = registry;
		this.exporters = new HashMap<String, ProxiedRmiExporter>();
	}

	public void setPort(int port)
	{
		this.port = port;
	}

	public int getPort()
	{
		return port;
	}

	@Override
	public void afterPropertiesSet() throws Exception
	{
		for(String serviceName : registry.getServiceNames())
		{
			if(registry.getServiceInterface(serviceName) != null)
			{
				log.debug("Registering service {}", serviceName);
				ProxiedRmiExporter exporter = new ProxiedRmiExporter(serviceName, registry);
				exporter.setRegistryPort(getPort());
				exporters.put(serviceName, exporter);
				exporter.afterPropertiesSet();
			}
		}
	}

	@Override
	public void destroy() throws Exception
	{
		for(ProxiedRmiExporter exporter : exporters.values())
		{
			exporter.destroy();
		}
	}
}
