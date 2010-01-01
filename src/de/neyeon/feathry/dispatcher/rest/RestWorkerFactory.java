package de.neyeon.feathry.dispatcher.rest;

import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

import de.neyeon.feathry.dispatcher.http.Worker;
import de.neyeon.feathry.dispatcher.http.WorkerFactory;
import de.neyeon.feathry.dispatcher.rpc.ServiceRegistry;

public class RestWorkerFactory implements WorkerFactory
{

	@Override
	public Worker create(Request request, Response response)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceRegistry getServiceDispatcher()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setServiceDispatcher(ServiceRegistry serviceDispatcher)
	{
		// TODO Auto-generated method stub
		
	}

}
