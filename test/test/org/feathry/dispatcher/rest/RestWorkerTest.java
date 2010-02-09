package test.org.feathry.dispatcher.rest;


import org.feathry.dispatcher.ThreadManager;
import org.feathry.dispatcher.http.SimpleHttpServer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test/org/feathry/dispatcher/testcontext.xml" })
public class RestWorkerTest
{
	@Autowired
	private ThreadManager threadManager;
	@Autowired
	private SimpleHttpServer httpServer;
	
	@Before
	public void setUp() throws Exception
	{
	}

	@Test
	public void testRest() throws InterruptedException
	{
		threadManager.offer(httpServer);
		while(true)
		{
			Thread.sleep(1000);
			Thread.yield();
		}
	}
}
