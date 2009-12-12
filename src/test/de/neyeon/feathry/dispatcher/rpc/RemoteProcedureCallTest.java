package test.de.neyeon.feathry.dispatcher.rpc;

import junit.framework.Assert;
import junit.framework.AssertionFailedError;
import junit.framework.Assert.*;

import org.junit.Test;

import test.de.neyeon.feathry.dispatcher.TestServiceImpl;

import de.neyeon.feathry.dispatcher.rpc.RemoteProcedureCall;

public class RemoteProcedureCallTest
{

	@Test
	public void testHasMethodObject()
	{
		TestServiceImpl dummy = new TestServiceImpl();
		// Service name not important for this test		
		RemoteProcedureCall rpc1 = new RemoteProcedureCall("Test", "voidTest", null);
		Assert.assertTrue(rpc1.canDispatchOn(dummy));
		Object[] args = { "Hi", 10 };
		RemoteProcedureCall rpc2 = new RemoteProcedureCall("Test", "argsTest", args);
		Class[] classes = { String.class, Integer.class };
		// System.out.println(Integer.class.isAssignableFrom(int.class));
		rpc2.canDispatchOn(dummy);
		
		Assert.assertTrue(rpc2.canDispatchOn(dummy));		
	}

}
