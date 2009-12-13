package test.de.neyeon.feathry.dispatcher.rpc;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.neyeon.feathry.dispatcher.rpc.RemoteProcedureCall;
import de.neyeon.feathry.dispatcher.rpc.ServiceDispatcher;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test/de/neyeon/feathry/dispatcher/testcontext.xml"})
public class ServiceDispatcherTest
{
	@Autowired
	ServiceDispatcher serviceDispatcher;
	
	@Test
	public void testInvokeHelloString()
	{
		try
		{
			Object[] args = { "Test" };
			RemoteProcedureCall rpc = new RemoteProcedureCall("testService", "hello", args);
			String result = (String) serviceDispatcher.invoke(rpc);
			assertEquals("Hello Test", result);
		} catch (Throwable e)
		{
			fail("Got exception " + e.getMessage());
		}
	}

	@Test
	public void testInvokeHelloStringString()
	{
		try
		{
			Object[] args = { "Hallo", "Test" };
			RemoteProcedureCall rpc = new RemoteProcedureCall("testService", "hello", args);
			String result = (String) serviceDispatcher.invoke(rpc);
			assertEquals("Hallo Test", result);
		} catch (Throwable e)
		{
			fail("Got exception " + e.getMessage());
		}
	}
	
	@Test
	public void testInterceptable()
	{
		try
		{
			Object[] args = { 4 };
			RemoteProcedureCall rpc = new RemoteProcedureCall("testService", "anyMethod", args);
			String result = (String) serviceDispatcher.invoke(rpc);
			String expected = "anyMethod(" + Arrays.toString(args) + ")";
			assertEquals(expected, result);
		} catch (Throwable e)
		{
			fail("Got exception " + e.getMessage());
		}
	}
}
