package de.neyeon.feathry.dispatcher.http;

import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

public abstract class Worker extends Thread
{
	private final Request request;
	private final Response response;
	
	protected Worker(Request request, Response response)
	{
		this.request = request;
		this.response = response;
	}
	
	public Request getRequest()
	{
		return request;
	}

	public Response getResponse()
	{
		return response;
	}
	
	@Override
	public abstract void run();
	
	public void handleError(Throwable e)
	{
		// TODO handle this properly
		e.printStackTrace();
	}
}
