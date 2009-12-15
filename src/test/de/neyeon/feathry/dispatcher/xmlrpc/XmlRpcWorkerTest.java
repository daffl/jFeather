package test.de.neyeon.feathry.dispatcher.xmlrpc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redstone.xmlrpc.XmlRpcClient;
import de.neyeon.feathry.dispatcher.ThreadManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test/de/neyeon/feathry/dispatcher/testcontext.xml",
		"/de/neyeon/feathry/dispatcher/config/default.xml" })
public class XmlRpcWorkerTest
{
	@Autowired
	ThreadManager threadManager;
	@Autowired
	Runnable httpServer;

	@Before
	public void setUp() throws Exception
	{
		threadManager.offer(httpServer);
		// Just to make sure that everything is started up
		Thread.sleep(1000);
	}

	@Test
	public void testTestServiceHelloStringString()
	{
		try
		{
			XmlRpcClient client = new XmlRpcClient("http://localhost:8080/xmlrpc", true);
			String result = (String) client.invoke("test.hello", new Object[] { "Hello", "World" });
			assertEquals("Hello World", result);
		} catch (Exception e)
		{
			e.printStackTrace();
			fail("Got error " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testTestServiceGetTestBean()
	{
		try
		{
			XmlRpcClient client = new XmlRpcClient("http://localhost:8080/xmlrpc", true);
			Object[] args = new Object[] { "Testbean", 10 };
			Map<String, Object> result = (Map<String, Object>) client.invoke("test.getTestBean", args);
			assertEquals("Testbean", result.get("name"));
			assertEquals(10, result.get("age"));
		} catch (Exception e)
		{
			e.printStackTrace();
			fail("Got error " + e.getMessage());
		}
	}
}