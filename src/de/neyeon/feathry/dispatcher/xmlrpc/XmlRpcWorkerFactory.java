package de.neyeon.feathry.dispatcher.xmlrpc;

import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

import redstone.xmlrpc.XmlRpcDispatcher;
import redstone.xmlrpc.XmlRpcServer;

import de.neyeon.feathry.dispatcher.http.Worker;
import de.neyeon.feathry.dispatcher.http.WorkerFactory;
import de.neyeon.feathry.dispatcher.rpc.ServiceDispatcher;

public class XmlRpcWorkerFactory implements WorkerFactory
{
	protected ServiceInvocationHandler invocationHandler;
	
	@Override
	public Worker create(Request request, Response response)
	{
		if(invocationHandler == null)
			throw new IllegalArgumentException("Invocation handler is null. Can not create worker.");
		XmlRpcServer server = new XmlRpcServer();
		server.addInvocationHandler("main", invocationHandler);
		String ip = request.getClientAddress().getAddress().getHostAddress();
		XmlRpcDispatcher dispatcher = new XmlRpcDispatcher(server, ip);
		return new XmlRpcWorker(request, response, dispatcher);
	}

	@Override
	public ServiceDispatcher getServiceDispatcher()
	{
		return invocationHandler.getServiceDispatcher();
	}

	@Override
	public void setServiceDispatcher(ServiceDispatcher serviceDispatcher)
	{
		invocationHandler = new ServiceInvocationHandler(serviceDispatcher);	
	}
	
}
