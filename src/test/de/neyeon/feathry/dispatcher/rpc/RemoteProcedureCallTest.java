package test.de.neyeon.feathry.dispatcher.rpc;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.de.neyeon.feathry.dispatcher.TestService;
import de.neyeon.feathry.dispatcher.rpc.RemoteProcedureCall;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test/de/neyeon/feathry/dispatcher/testcontext.xml"})
public class RemoteProcedureCallTest
{
	@Autowired
	TestService testService;
	
	@Test
	public void testCanDispatchOnObject()
	{
		assertNotNull(testService);
		try
		{
			Object[] args = { "Test" };
			RemoteProcedureCall rpc = new RemoteProcedureCall("testService", "hello", args);
			Method m = rpc.getDispatchableMethod(testService);
			assertNotNull(m);		
		} catch (NoSuchMethodException e)
		{
			fail("Got unexpected no such method exception");
		}
	}
	
	@Test
	public void testCannotDispatchOnObject()
	{
		try
		{
			Object[] args = { "Test1", "Test2" };
			RemoteProcedureCall rpc = new RemoteProcedureCall("testService", "hello", args);
			rpc.getDispatchableMethod(testService);
			fail("Exception expected");
		} catch (NoSuchMethodException e)
		{
			
		}
	}

}
