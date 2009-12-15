package de.neyeon.feathry.dispatcher.xmlrpc;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.log4j.Logger;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

import redstone.xmlrpc.XmlRpcDispatcher;
import de.neyeon.feathry.dispatcher.http.Worker;

/**
 * The worker implementation for XML RPC requests. Uses the Redstone XML-RPC library.
 * @author David Luecke (daff@neyeon.de)
 */
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
			Logger.getLogger(this.getClass()).debug("XmlRpcWorker started... trying to dispatch the stuff");
			Writer out = new OutputStreamWriter(this.getResponse().getOutputStream());
			InputStream in = this.getRequest().getInputStream();
			dispatcher.dispatch(in, out);
			in.close();
			out.flush();
			out.close();
		} catch (Exception e)
		{
			this.handleError(e);
		}
	}

}
