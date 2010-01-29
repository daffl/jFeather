package test.de.neyeon.feathry.dispatcher.rest;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.neyeon.feathry.dispatcher.ThreadManager;
import de.neyeon.feathry.dispatcher.http.SimpleHttpServer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test/de/neyeon/feathry/dispatcher/testcontext.xml" })
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
	public void testRest()
	{
		threadManager.offer(httpServer);
		while(true);
	}
}
