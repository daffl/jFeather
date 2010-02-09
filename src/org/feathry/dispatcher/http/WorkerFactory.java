package org.feathry.dispatcher.http;

import org.feathry.dispatcher.rpc.ServiceRegistry;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;


public interface WorkerFactory
{
	public Worker create(Request request, Response response);
	public void setServiceRegistry(ServiceRegistry serviceRegistry);
	public ServiceRegistry getServiceRegistry();
}
