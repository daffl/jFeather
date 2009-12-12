package de.neyeon.feathry.dispatcher.xmlrpc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

import redstone.xmlrpc.XmlRpcDispatcher;
import redstone.xmlrpc.XmlRpcException;

import de.neyeon.feathry.dispatcher.http.Worker;

public class XmlRpcWorker extends Worker
{
	protected XmlRpcDispatcher dispatcher;
	
	protected XmlRpcWorker(Request request, Response response, XmlRpcDispatcher dispatcher)
	{
		super(request, response);
		this.dispatcher = dispatcher;
	}

	@Override
	public void run()
	{
		try
		{
			Writer out = new OutputStreamWriter(this.getResponse().getOutputStream());
			InputStream in = this.getRequest().getInputStream();
			dispatcher.dispatch(in, out);
			in.close();
			out.flush();
			out.close();
		} catch (XmlRpcException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
