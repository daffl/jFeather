package test.de.neyeon.feathry.dispatcher.rmi;


import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.de.neyeon.feathry.dispatcher.services.TestService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test/de/neyeon/feathry/dispatcher/testcontext.xml" })
public class RmiRegistryTest
{
	@Test
	public void testRmiRegistryTestService() throws InterruptedException
	{
		/* 
		<bean depends-on="rmiRegistry" name="rmiTestService" class="org.springframework.remoting.rmi.RmiProxyFactoryBean">
		<property name="serviceUrl" value="rmi://localhost:1099/test" />
		<property name="serviceInterface" value="test.de.neyeon.feathry.dispatcher.services.TestService" />
		</bean>
		*/
		Thread.sleep(1000);
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/test/de/neyeon/feathry/dispatcher/rmi/rmiconfig.xml");
		Object proxy = context.getBean("rmiTestService");
		TestService ts = (TestService) proxy;
		System.out.println(ts.hello("Hello", "world"));
		
		/*
		RmiProxyFactoryBean proxy = new RmiProxyFactoryBean();
		proxy.setServiceUrl("rmi://localhost:1099/test");
		proxy.setServiceInterface(TestService.class);
		System.out.println(proxy.getRemoteInvocationFactory());
		// assertTrue(proxy instanceof TestService);
		TestService ts = (TestService) proxy;
		assertEquals("Hello world", ts.hello("Hello", "world"));
		*/
	}
}
