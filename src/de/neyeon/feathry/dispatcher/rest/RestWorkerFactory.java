package de.neyeon.feathry.dispatcher.rest;

import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

import de.neyeon.feathry.dispatcher.http.Worker;
import de.neyeon.feathry.dispatcher.http.WorkerFactory;
import de.neyeon.feathry.dispatcher.rpc.ServiceRegistry;

public class RestWorkerFactory implements WorkerFactory
{
	private ServiceRegistry registry;
	private ResourceLocator locator;

	@Override
	public Worker create(Request request, Response response)
	{
		return new RestWorker(request, response);
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
	}

}
