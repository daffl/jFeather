package de.neyeon.feathry.dispatcher.rest;

import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

import de.neyeon.feathry.dispatcher.http.Worker;
import de.neyeon.feathry.dispatcher.http.WorkerFactory;
import de.neyeon.feathry.dispatcher.rpc.ServiceDispatcher;

public class RestWorkerFactory implements WorkerFactory
{

	@Override
	public Worker create(Request request, Response response)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceDispatcher getServiceDispatcher()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setServiceDispatcher(ServiceDispatcher serviceDispatcher)
	{
		// TODO Auto-generated method stub
		
	}

}
