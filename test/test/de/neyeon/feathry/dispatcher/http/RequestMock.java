package test.de.neyeon.feathry.dispatcher.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.channels.ReadableByteChannel;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.simpleframework.http.Address;
import org.simpleframework.http.ContentType;
import org.simpleframework.http.Cookie;
import org.simpleframework.http.Form;
import org.simpleframework.http.Part;
import org.simpleframework.http.Path;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;
import org.simpleframework.http.session.Session;
import org.simpleframework.util.lease.LeaseException;

public class RequestMock implements Request
{

	@Override
	public Object getAttribute(Object arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map getAttributes()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReadableByteChannel getByteChannel() throws IOException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InetSocketAddress getClientAddress()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getContent() throws IOException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Form getForm() throws IOException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getInputStream() throws IOException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getParameter(String arg0) throws IOException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Part getPart(String arg0) throws IOException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Session getSession() throws LeaseException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Session getSession(boolean arg0) throws LeaseException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isKeepAlive()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSecure()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(String arg0)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getContentLength()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ContentType getContentType()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cookie getCookie(String arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cookie> getCookies()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getDate(String arg0)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getInteger(String arg0)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Locale> getLocales()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getNames()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue(String arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getValues(String arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Address getAddress()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMajor()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getMethod()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMinor()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Path getPath()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query getQuery()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTarget()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
