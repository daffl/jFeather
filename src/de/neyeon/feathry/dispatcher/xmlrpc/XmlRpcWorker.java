package de.neyeon.feathry.dispatcher.xmlrpc;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redstone.xmlrpc.XmlRpcDispatcher;
import de.neyeon.feathry.dispatcher.http.Worker;

/**
 * The worker implementation for XML RPC requests. Uses the Redstone XML-RPC library.
 * @author David Luecke (daff@neyeon.de)
 */
public class XmlRpcWorker extends Worker
{
	private Logger log = LoggerFactory.getLogger(this.getClass());
	protected XmlRpcDispatcher dispatcher;

	public XmlRpcWorker(Request request, Response response, XmlRpcDispatcher dispatcher)
	{		
		super(request, response);
		this.dispatcher = dispatcher;
	}

	@Override
	public void run()
	{
		try
		{
			log.debug("Starting XML-RPC worker thread request from host {}", dispatcher.getCallerIp());
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
