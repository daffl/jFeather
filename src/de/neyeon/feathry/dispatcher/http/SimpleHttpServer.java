package de.neyeon.feathry.dispatcher.http;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import org.simpleframework.http.core.Container;
import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;

public class SimpleHttpServer implements Runnable
{
	private int port;
	private Container container;

	@Override
	public void run()
	{
		try
		{
			Connection connection = new SocketConnection(container);
			SocketAddress address = new InetSocketAddress(port);
			connection.connect(address);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getPort()
	{
		return port;
	}

	public void setPort(int port)
	{
		this.port = port;
	}

	public Container getContainer()
	{
		return container;
	}

	public void setContainer(Container container)
	{
		this.container = container;
	}
	
}
