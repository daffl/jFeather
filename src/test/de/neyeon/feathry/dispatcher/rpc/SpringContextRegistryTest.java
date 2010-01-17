package test.de.neyeon.feathry.dispatcher.rpc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.de.neyeon.feathry.dispatcher.beans.TestBean;
import de.neyeon.feathry.dispatcher.rpc.RemoteProcedureCall;
import de.neyeon.feathry.dispatcher.rpc.ServiceRegistry;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test/de/neyeon/feathry/dispatcher/testcontext.xml" })
public class SpringContextRegistryTest
{
	@Autowired
	ServiceRegistry serviceRegistry;

	@Test
	public void testInvokeHelloString()
	{
		try
		{
			Object[] args = { "Test" };
			RemoteProcedureCall rpc = new RemoteProcedureCall("test", "hello", args);
			String result = (String) serviceRegistry.invoke(rpc);
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
			RemoteProcedureCall rpc = new RemoteProcedureCall("test", "hello", args);
			String result = (String) serviceRegistry.invoke(rpc);
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
			RemoteProcedureCall rpc = new RemoteProcedureCall("interceptable", "anyMethod", args);
			String result = (String) serviceRegistry.invoke(rpc);
			String expected = "anyMethod(" + Arrays.toString(args) + ")";
			assertEquals(expected, result);
		} catch (Throwable e)
		{
			fail("Got exception " + e.getMessage());
		}
	}
	
	@Test
	public void testInvokeGetTestBean()
	{
		try
		{
			Object[] args = { "Testbean", 1 };
			RemoteProcedureCall rpc = new RemoteProcedureCall("test", "getTestBean", args);
			TestBean result = (TestBean) serviceRegistry.invoke(rpc);
			TestBean expected = new TestBean("Testbean", 1);
			assertEquals(expected, result);
		} catch (Throwable e)
		{
			fail("Got exception " + e.getMessage());
		}		
	}	
}
