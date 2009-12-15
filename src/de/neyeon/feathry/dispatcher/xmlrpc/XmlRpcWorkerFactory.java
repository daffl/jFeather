package de.neyeon.feathry.dispatcher.xmlrpc;

import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

import redstone.xmlrpc.XmlRpcDispatcher;
import redstone.xmlrpc.XmlRpcInvocationHandler;
import redstone.xmlrpc.XmlRpcServer;
import de.neyeon.feathry.dispatcher.http.Worker;
import de.neyeon.feathry.dispatcher.http.WorkerFactory;
import de.neyeon.feathry.dispatcher.rpc.ServiceDispatcher;

/**
 * The worker factory implementation for XML remote procedure calls. Uses the XML RPC implementation
 * from the redstone XML RPC library.
 * @author David Luecke (daff@neyeon.de)
 */
public class XmlRpcWorkerFactory implements WorkerFactory
{
	protected ServiceDispatcher serviceDispatcher;
	protected XmlRpcServer server;

	/* (non-Javadoc)
	 * @see de.neyeon.feathry.dispatcher.http.WorkerFactory#create(org.simpleframework.http.Request, org.simpleframework.http.Response)
	 */
	@Override
	public Worker create(Request request, Response response)
	{
		if (serviceDispatcher == null)
			throw new IllegalArgumentException("No service dispatcher set");		
		String ip = request.getClientAddress().getAddress().getHostAddress();
		XmlRpcDispatcher dispatcher = new XmlRpcDispatcher(server, ip);
		return new XmlRpcWorker(request, response, dispatcher);
	}

	/* (non-Javadoc)
	 * @see de.neyeon.feathry.dispatcher.http.WorkerFactory#getServiceDispatcher()
	 */
	@Override
	public ServiceDispatcher getServiceDispatcher()
	{
		return serviceDispatcher;
	}

	/* (non-Javadoc)
	 * @see de.neyeon.feathry.dispatcher.http.WorkerFactory#setServiceDispatcher(de.neyeon.feathry.dispatcher.rpc.ServiceDispatcher)
	 */
	@Override
	public void setServiceDispatcher(ServiceDispatcher serviceDispatcher)
	{
		this.serviceDispatcher = serviceDispatcher;
		this.initInvocationHandlers();
	}
	
	protected void initInvocationHandlers()
	{
		server = new XmlRpcServer();
		for(String name : serviceDispatcher.getServiceNames())
		{
			XmlRpcInvocationHandler handler = new ServiceInvocationHandler(name, serviceDispatcher);
			server.addInvocationHandler(name, handler);
		}
	}

}
