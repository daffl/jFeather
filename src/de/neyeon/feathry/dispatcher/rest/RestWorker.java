package de.neyeon.feathry.dispatcher.rest;

import java.io.IOException;
import java.io.PrintStream;

import org.simpleframework.http.Path;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.neyeon.feathry.dispatcher.http.Worker;

public class RestWorker extends Worker
{
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public RestWorker(Request request, Response response)
	{
		super(request, response);
	}

	@Override
	public void run()
	{
		final Response response = this.getResponse();
		try
		{
			Path path = getRequest().getPath();
			response.set("Content-Type", "text/plain");
			response.set("Server", "HelloWorld/1.0 (Simple 4.0)");
			Long time = System.currentTimeMillis();
			response.setDate("Date", time);
			response.setDate("Last-Modified", time);
			PrintStream body = response.getPrintStream();
			for(String arg : path.getSegments())
			{
				body.println(arg);
			}			
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
