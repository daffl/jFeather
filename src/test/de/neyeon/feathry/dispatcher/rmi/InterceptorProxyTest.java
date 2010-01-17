package test.de.neyeon.feathry.dispatcher.rmi;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.de.neyeon.feathry.dispatcher.services.TestService;

import de.neyeon.feathry.dispatcher.rmi.InterceptorProxy;
import de.neyeon.feathry.dispatcher.rpc.ServiceRegistry;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test/de/neyeon/feathry/dispatcher/testcontext.xml" })
public class InterceptorProxyTest
{
	@Autowired
	ServiceRegistry serviceRegistry;

	@Test
	public void testInvoke()
	{
		InterceptorProxy proxy = new InterceptorProxy("test", serviceRegistry);
		assertTrue(proxy.getObject() instanceof TestService);
		TestService service = (TestService) proxy.getObject();
		String result = service.hello("Hello", "world");
		assertEquals("Hello world", result);
	}

}
