package de.neyeon.feathry.dispatcher.xmlrpc;

import java.util.List;

import org.apache.log4j.Logger;

import redstone.xmlrpc.XmlRpcInvocationHandler;
import de.neyeon.feathry.dispatcher.rpc.RemoteProcedureCall;
import de.neyeon.feathry.dispatcher.rpc.ServiceDispatcher;

public class ServiceInvocationHandler implements XmlRpcInvocationHandler
{
	protected final Logger log = Logger.getLogger(this.getClass());
	
	protected final ServiceDispatcher serviceDispatcher;
	protected final String serviceName;
	
	public ServiceInvocationHandler(String serviceName, ServiceDispatcher serviceDispatcher)
	{
		this.serviceDispatcher = serviceDispatcher;
		this.serviceName = serviceName;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Object invoke(String methodname, List arguments) throws Throwable
	{
		log.debug("Handling invocation of " + methodname + " with " + arguments);
		RemoteProcedureCall rpc = new RemoteProcedureCall(serviceName, methodname, arguments.toArray());		
		return serviceDispatcher.invoke(rpc);
	}
}
