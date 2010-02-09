package org.feathry.dispatcher.rest;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.Arrays;

import org.feathry.dispatcher.http.Worker;
import org.feathry.dispatcher.rpc.RemoteProcedureCall;
import org.feathry.dispatcher.rpc.ServiceRegistry;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RestWorker extends Worker
{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final ServiceRegistry registry;
	
	public RestWorker(Request request, Response response, ServiceRegistry registry)
	{
		super(request, response);
		this.registry = registry;
	}

	@Override
	public void run()
	{
		final Response response = this.getResponse();
		final Request request = this.getRequest();
		try
		{
			String[] pathSegments = request.getPath().getSegments();			
			String responseText = "Something went wrong";
			if(pathSegments.length > 1)
			{
				String serviceName = pathSegments[1];
				String method = request.getMethod();
				if(method.toLowerCase().equals("get"))
				{
					RemoteProcedureCall rpc;
					if(pathSegments.length == 2)
					{
						 rpc = new RemoteProcedureCall(serviceName, "getAll", new Object[] {});
					}
					else
					{
						Object[] id = { Serializable.class.cast(pathSegments[2]) };
						rpc = new RemoteProcedureCall(serviceName, "get", id);
					}
					
					try
					{
						Object result = registry.invoke(rpc);
						responseText = result.toString();
					} catch (Throwable e)
					{
						e.printStackTrace();
						responseText = "Something went really wrong: " + e.getMessage();
					}				
				}
			}
			
			Long time = System.currentTimeMillis();
			response.set("Content-Type", "text/plain");
			response.setDate("Date", time);
			response.setDate("Last-Modified", time);
			PrintStream body = response.getPrintStream();
			body.println(responseText);
			body.close();
			
		} catch (IOException e)
		{
			log.warn("Got exception", e);
			if (log.isDebugEnabled())
			{
				e.printStackTrace();
			}
		}
	}

}
