package org.feathry.dispatcher.http;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import org.simpleframework.http.core.Container;
import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A simple Runnable implementation that runs the SimpleFramework http server
 * with a given container.
 * @author David Luecke (daff@neyeon.de)
 */
public class SimpleHttpServer implements Runnable
{
	private int port;
	private Container container;
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		try
		{
			log.info("Starting Simpleframework HTTP server on port {}", port);
			Connection connection = new SocketConnection(container);
			SocketAddress address = new InetSocketAddress(port);
			connection.connect(address);
		} catch (IOException e)
		{
			log.error("IOException while running Simpleframework HTTP server", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Returns the port the server will be running on
	 * @return Server port
	 */
	public int getPort()
	{
		return port;
	}

	/**
	 * Set the port the server will be running on
	 * @param port The server port to use
	 */
	public void setPort(int port)
	{
		this.port = port;
	}

	/**
	 * Returns the Simpleframework container that will handle incoming requests
	 * @return Container handling incoming requests
	 */
	public Container getContainer()
	{
		return container;
	}

	/**
	 * Returns the Simpleframework container that will handle incoming requests
	 * @param container The container to set
	 */
	public void setContainer(Container container)
	{
		this.container = container;
	}

}
