package org.feathry.dispatcher.xmlrpc;

import java.util.List;

import org.feathry.dispatcher.rpc.RemoteProcedureCall;
import org.feathry.dispatcher.rpc.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redstone.xmlrpc.XmlRpcInvocationHandler;

public class InvocationHandlerDecorator implements XmlRpcInvocationHandler
{
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	protected final ServiceRegistry dispatcher;
	protected final String serviceName;
	
	public InvocationHandlerDecorator(String serviceName, ServiceRegistry dispatcher)
	{
		this.dispatcher = dispatcher;
		this.serviceName = serviceName;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Object invoke(String methodname, List arguments) throws Throwable
	{
		log.debug("Service '{}' handling XMl-RPC invocation of '{}'", serviceName, methodname);
		RemoteProcedureCall rpc = new RemoteProcedureCall(serviceName, methodname, arguments.toArray());		
		return dispatcher.invoke(rpc);
	}
}
