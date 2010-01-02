package test.de.neyeon.feathry.dispatcher.rpc;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.de.neyeon.feathry.dispatcher.beans.TestBean;
import test.de.neyeon.feathry.dispatcher.services.InterceptableService;
import test.de.neyeon.feathry.dispatcher.services.TestService;
import de.neyeon.feathry.dispatcher.rpc.RemoteProcedureCall;
import de.neyeon.feathry.dispatcher.rpc.ServiceDispatcher;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test/de/neyeon/feathry/dispatcher/testcontext.xml",
		"/de/neyeon/feathry/dispatcher/config/default.xml" })
public class ServiceDispatcherTest
{
	@Autowired
	TestService testService; 

	@Test
	public void testInvoke() throws Throwable
	{
		Map<Object, Object> beanMap = new HashMap<Object, Object>();
		beanMap.put("name", "TestBean");
		beanMap.put("age", 23);
		TestBean tb = new TestBean();
		tb.setName("TestBean");
		tb.setAge(23);
		ServiceDispatcher dispatcher = new ServiceDispatcher(testService);
		Object[] args = { beanMap };
		RemoteProcedureCall rpc = new RemoteProcedureCall("test", "save", args);
		assertEquals(tb.toString(), dispatcher.invoke(rpc));
	}

	@Test
	public void testInvokeInterceptable() throws Throwable
	{
		ServiceDispatcher dispatcher = new ServiceDispatcher(new InterceptableService());
		Object[] args = { "Hello", "Test" };
		RemoteProcedureCall rpc = new RemoteProcedureCall("test", "doSomething", args);
		String expected = "doSomething(" + Arrays.toString(args) + ")";
		assertEquals(expected, dispatcher.invoke(rpc));
	}
	
	public void testNotFound() throws Throwable
	{
		ServiceDispatcher dispatcher = new ServiceDispatcher(testService);
		Object[] args = { "Hello", "Test" };
		RemoteProcedureCall rpc = new RemoteProcedureCall("test", "noMethod", args);
		try
		{
			dispatcher.invoke(rpc);
			fail("Exptected NoSuchMethodException");
		}
		catch(NoSuchMethodException e)
		{
			
		}
	}
}
