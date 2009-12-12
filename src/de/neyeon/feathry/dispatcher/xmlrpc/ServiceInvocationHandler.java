package de.neyeon.feathry.dispatcher.xmlrpc;

import java.util.List;

import redstone.xmlrpc.XmlRpcInvocationHandler;
import de.neyeon.feathry.dispatcher.rpc.RemoteProcedureCall;
import de.neyeon.feathry.dispatcher.rpc.ServiceDispatcher;

public class ServiceInvocationHandler implements XmlRpcInvocationHandler
{
	private final ServiceDispatcher serviceDispatcher;
	
	public ServiceInvocationHandler(ServiceDispatcher serviceDispatcher)
	{
		this.serviceDispatcher = serviceDispatcher;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Object invoke(String methodname, List arguments) throws Throwable
	{
		String[] split = methodname.split(".");
		String service = split[0];
		String method = split[1];
		RemoteProcedureCall rpc = new RemoteProcedureCall(service, method, arguments.toArray());		
		return serviceDispatcher.invoke(rpc);
	}
	
	public ServiceDispatcher getServiceDispatcher()
	{
		return serviceDispatcher;
	}
}
