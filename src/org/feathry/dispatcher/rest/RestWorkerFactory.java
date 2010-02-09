package org.feathry.dispatcher.rest;

import org.feathry.dispatcher.http.Worker;
import org.feathry.dispatcher.http.WorkerFactory;
import org.feathry.dispatcher.rpc.ServiceRegistry;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;


public class RestWorkerFactory implements WorkerFactory
{
	private ServiceRegistry registry;

	@Override
	public Worker create(Request request, Response response)
	{
		return new RestWorker(request, response, registry);
	}

	@Override
	public ServiceRegistry getServiceRegistry()
	{
		return registry;
	}

	@Override
	public void setServiceRegistry(ServiceRegistry registry)
	{
		this.registry = registry;
		// TODO find all service that implement the resource interface have
		// resource annotation or are hibernate entities
		this.initResourceLocator();
	}
	
	protected void initResourceLocator()
	{
		
	}

}
