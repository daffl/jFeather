package test.de.neyeon.feathry.domain;

import static org.junit.Assert.*;

import de.neyeon.feathry.domain.DynamicGormDispatcher;

import org.junit.Before;

import de.neyeon.feathry.ServiceFactory;
import org.junit.Test;

class DynamicGormDispatcherTest
{
	@Before
	public void setUp()
	{
		// Initialize the Spring application context
		ServiceFactory.getInstance();
	}
	
	@Test
	public void testGetInterfaceClassOfQ()
	{
		DynamicGormDispatcher disp = new DynamicGormDispatcher()
		TestInterface ti = disp.getInterface(TestInterface.class)
		assert ti != null
		assert ti instanceof TestInterface
		// Mock the dispatch method to see if it is beeing called correctly
		disp.metaClass.dispatch = { String name, Object[] args ->
			"$name successfull".toString()
		}
		assert ti.hello("world") == "hello successfull"		
	}

	@Test
	public void testDispatch()
	{
		// TODO implement this
		fail("Not implemented")
	}

	@Test
	public void testGetInterfaceString()
	{
		DynamicGormDispatcher disp = new DynamicGormDispatcher()
		assert disp.getInterface("test.de.neyeon.feathry.domain.TestInterface") instanceof TestInterface
	}

}