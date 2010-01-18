package de.neyeon.feathry.dispatcher.rpc;

import java.util.List;

public interface ServiceRegistry
{
	public Object invoke(RemoteProcedureCall rpc) throws Throwable;
	public List<String> getServiceNames();
	public Object getService(String serviceName);
	public void attachInterface(String serviceName, Class<?> serviceInterface);
	public Class<?> getServiceInterface(String serviceName);
	public Object getServiceProxy(String serviceName);
}