package test.org.feathry.dispatcher.xmlrpc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.feathry.dispatcher.rpc.ServiceRegistry;
import org.feathry.dispatcher.xmlrpc.InvocationHandlerDecorator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redstone.xmlrpc.XmlRpcInvocationHandler;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test/org/feathry/dispatcher/testcontext.xml" })
public class InvocationHandlerDecoratorTest
{
	@Autowired
	ServiceRegistry serviceDispatcher;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testInvoke()
	{
		try
		{
			assertNotNull(serviceDispatcher);
			XmlRpcInvocationHandler handler = new InvocationHandlerDecorator("test", serviceDispatcher);
			List args = new ArrayList();
			args.add("Hello");
			args.add("Test");
			String result = (String) handler.invoke("hello", args);
			String expected = "Hello Test";
			assertEquals(result, expected);
		} catch (Throwable e)
		{
			e.printStackTrace();
			fail("Got exception " + e.getMessage());
		}
	}
}
