package de.neyeon.feathry.dispatcher.http;

import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

import de.neyeon.feathry.dispatcher.rpc.ServiceDispatcher;

public interface WorkerFactory
{
	public Worker create(Request request, Response response);
	public void setServiceDispatcher(ServiceDispatcher serviceDispatcher);
	public ServiceDispatcher getServiceDispatcher();
}
