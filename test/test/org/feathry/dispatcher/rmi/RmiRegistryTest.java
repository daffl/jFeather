package test.org.feathry.dispatcher.rmi;


import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.org.feathry.dispatcher.services.TestService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test/org/feathry/dispatcher/testcontext.xml" })
public class RmiRegistryTest
{
	@Autowired
	TestService rmiTestService;
	
	@Test
	public void testRmiRegistryTestService() throws InterruptedException
	{
		assertEquals("Hello world", rmiTestService.hello("Hello", "world"));
	}
}
