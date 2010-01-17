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
import test.de.neyeon.feathry.dispatcher.beans.OtherTestBean;
import test.de.neyeon.feathry.dispatcher.beans.TestBean;
import de.neyeon.feathry.beans.ExtendedDynaBean;
import de.neyeon.feathry.dispatcher.ThreadManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test/de/neyeon/feathry/dispatcher/testcontext.xml" })
public class XmlRpcWorkerTest
{
	static boolean serverIsRunning = false;
	
	@Autowired
	ThreadManager threadManager;
	@Autowired
	Runnable httpServer;

	@Before
	public void setUp() throws Exception
	{
		if(!serverIsRunning) // Start server only once
		{
			threadManager.offer(httpServer);
			// Just to make sure that everything is started up
			Thread.sleep(1000);
			serverIsRunning = true;
		}
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
	
	@Test
	public void testTestServiceSaveTestBean()
	{
		try
		{
			TestBean tb = new TestBean();
			tb.setName("Test");
			tb.setAge(23);
			
			XmlRpcClient client = new XmlRpcClient("http://localhost:8080/xmlrpc", true);
			Object[] args = new Object[] { tb };
			String result = (String) client.invoke("test.save", args);
			assertEquals(tb.toString(), result);
		} catch (Exception e)
		{
			e.printStackTrace();
			fail("Got error " + e.getMessage());
		}		
	}
	
	@Test
	public void testTestServiceSaveOtherTestBean()
	{
		try
		{
			OtherTestBean otb = new OtherTestBean();
			otb.setName("Test");
			otb.setAge(23);
			otb.setDate(1);

			XmlRpcClient client = new XmlRpcClient("http://localhost:8080/xmlrpc", true);
			Object[] args = new Object[] { otb };
			String result = (String) client.invoke("test.save", args);
			assertEquals(otb.toString(), result);
		} catch (Exception e)
		{
			e.printStackTrace();
			fail("Got error " + e.getMessage());
		}		
	}
	
	@SuppressWarnings("unchecked")
	public void testUpdateDateOtherTestBean()
	{
		try
		{
			OtherTestBean otb = new OtherTestBean();
			otb.setName("Test");
			otb.setAge(23);

			XmlRpcClient client = new XmlRpcClient("http://localhost:8080/xmlrpc", true);
			Object[] args = new Object[] { otb };
			Map<Object, Object> resultMap = (Map<Object, Object>) client.invoke("test.updateDate", args);
			OtherTestBean result = new ExtendedDynaBean(resultMap).getBean(OtherTestBean.class);
			assertEquals(1000000, result.getDate());
		} catch (Exception e)
		{
			e.printStackTrace();
			fail("Got error " + e.getMessage());
		}			
	}
}