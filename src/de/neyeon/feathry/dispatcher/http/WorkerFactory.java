package de.neyeon.feathry.dispatcher.http;

import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

import de.neyeon.feathry.dispatcher.rpc.ServiceRegistry;

public interface WorkerFactory
{
	public Worker create(Request request, Response response);
	public void setServiceDispatcher(ServiceRegistry serviceDispatcher);
	public ServiceRegistry getServiceDispatcher();
}
