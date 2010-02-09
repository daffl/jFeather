package test.org.feathry.dispatcher.rpc;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.Date;

import org.feathry.dispatcher.rpc.RemoteProcedureCall;
import org.junit.Test;


public class RemoteProcedureCallTest
{
	@Test
	public void testTypesAssignableTo()
	{
		Object[] args = { "Hello", "World" };
		RemoteProcedureCall rpc = new RemoteProcedureCall("test", "test", args);
		Class<?>[] toCompare = { Serializable.class, Serializable.class };
		assertTrue(rpc.isAssignableTo(toCompare));
		Class<?>[] toCompare2 = { String.class, Object.class };
		assertTrue(rpc.isAssignableTo(toCompare2));
		Class<?>[] toCompare3 = { String.class, Date.class };
		assertFalse(rpc.isAssignableTo(toCompare3));
	}

}
