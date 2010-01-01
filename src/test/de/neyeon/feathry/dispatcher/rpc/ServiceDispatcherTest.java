package test.de.neyeon.feathry.dispatcher.rpc;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test/de/neyeon/feathry/dispatcher/testcontext.xml",
		"/de/neyeon/feathry/dispatcher/config/default.xml" })
public class ServiceDispatcherTest
{

	@Before
	public void setUp() throws Exception
	{
	}

	@Test
	public void testInvoke()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testInvokeAmbiguous()
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void testInvokeInterceptable()
	{
		fail("Not yet implemented");
	}
}
