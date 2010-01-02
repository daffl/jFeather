package test.de.neyeon.feathry.dispatcher.http;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.channels.WritableByteChannel;
import java.util.List;

import org.simpleframework.http.ContentType;
import org.simpleframework.http.Cookie;
import org.simpleframework.http.Response;

public class ResponseMock implements Response
{

	@Override
	public void close() throws IOException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void commit() throws IOException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public WritableByteChannel getByteChannel() throws IOException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WritableByteChannel getByteChannel(int arg0) throws IOException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OutputStream getOutputStream() throws IOException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OutputStream getOutputStream(int arg0) throws IOException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrintStream getPrintStream() throws IOException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrintStream getPrintStream(int arg0) throws IOException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCommitted()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reset() throws IOException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setContentLength(int arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void add(String arg0, String arg1)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void add(String arg0, int arg1)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void addDate(String arg0, long arg1)
	{
		// TODO Auto-generated method stub

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
	public List<String> getNames()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTransferEncoding()
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
	public void remove(String arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void set(String arg0, String arg1)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void set(String arg0, int arg1)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public Cookie setCookie(Cookie arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cookie setCookie(String arg0, String arg1)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDate(String arg0, long arg1)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public int getCode()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMajor()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMinor()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getText()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCode(int arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setMajor(int arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setMinor(int arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setText(String arg0)
	{
		// TODO Auto-generated method stub

	}

}
