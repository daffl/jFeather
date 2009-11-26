package test.de.neyeon.feathry.domain;

import static org.junit.Assert.*;

import de.neyeon.feathry.domain.DynamicGormDispatcher;

import org.junit.Before;

import java.lang.reflect.ParameterizedType;

import java.lang.reflect.Type;

import de.neyeon.feathry.ServiceFactory;
import java.lang.reflect.Method;

import org.junit.Test;

import sun.reflect.generics.reflectiveObjects.*;

class DynamicGormDispatcherTest
{
	static def doTest()
	{
		System.out.println("This is a static invocation");
	}
	
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
		disp.metaClass.dispatch = { Method m, Object[] args ->
			"${m.name} successfull with $args".toString()
		}
		assert ti.hello("world") == "hello successfull with [world]"		
	}

	@Test
	public void testDispatch()
	{
		DynamicGormDispatcher disp = new DynamicGormDispatcher()
		TestInterface ti = disp.getInterface(TestInterface.class)
		// This should call the static getAll method on DummyDomainObject
		// Which is the generic return type of TestInterface.getAll()		
		assert ti.getAll() == MockDomainObject.getAll()
		// Test it with arguments
		def findDummyList = [new MockDomainObject(name : "FindDummy", saved : true)]
		assert ti.findByNameAndSaved("FindDummy", true) == findDummyList

		// Test invoking a method on the object
		def saveDummy = new MockDomainObject(name : "SaveDummy")
		assert saveDummy.saved == false
		ti.save(saveDummy)
		assert saveDummy.saved == true
	}

	@Test
	public void testGetInterfaceString()
	{
		DynamicGormDispatcher disp = new DynamicGormDispatcher()
		assert disp.getInterface("test.de.neyeon.feathry.domain.TestInterface") instanceof TestInterface
	}

}