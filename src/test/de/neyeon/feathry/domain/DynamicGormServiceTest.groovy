package test.de.neyeon.feathry.domain;

import static org.junit.Assert.*;

import de.neyeon.feathry.domain.DynamicGormService;
import de.neyeon.feathry.user.Authority;
import de.neyeon.feathry.user.User;

import org.junit.Before;



import de.neyeon.feathry.ServiceFactory;
import java.lang.reflect.Method;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sun.reflect.generics.reflectiveObjects.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations=["/de/neyeon/feathry/config/default.xml"])
class DynamicGormServiceTest
{
	@Test
	public void testGetInterfaceClassOfQ()
	{
		DynamicGormService disp = new DynamicGormService()
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
	public void testDispatchStatic()
	{
		DynamicGormService disp = new DynamicGormService()
		TestInterface ti = disp.getInterface(TestInterface.class)
		
		// This should call the static getAll method on DummyDomainObject
		// Which is the generic return type of TestInterface.getAll()		
		assert ti.getAll() == MockDomainObject.getAll()
		// Test it with arguments
		def findDummyList = [new MockDomainObject(name : "FindDummy", saved : true)]
		assert ti.findByNameAndSaved("FindDummy", true) == findDummyList
	}
	
	@Test
	public void testDispatchObject()
	{
		DynamicGormService disp = new DynamicGormService()
		TestInterface ti = disp.getInterface(TestInterface.class)
		
		// Test invoking a method on the object
		def saveDummy = new MockDomainObject(name : "SaveDummy")
		assert saveDummy.saved == false
		ti.save(saveDummy)
		assert saveDummy.saved == true
		def saveDummy2 = new MockDomainObject(name : "SaveDummy2")
		ti.saveAndChangeName(saveDummy2, "SavedDummy")
		assert saveDummy2 == new MockDomainObject(name : "SavedDummy", saved: true)
	}
	
	@Test
	public void testGetInterfaceString()
	{
		DynamicGormService disp = new DynamicGormService()
		assert disp.getInterface("test.de.neyeon.feathry.domain.TestInterface") instanceof TestInterface
	}
	
	@Test
	public void testDispatchOnEntity()
	{	
		DynamicGormService disp = new DynamicGormService()
		TestInterface ti = disp.getInterface(TestInterface.class)		
		User usr = new User(username : "Testuser", password : "Test123")
		assert usr.id == null
		ti.save(usr)
		assert usr.id != null
	}

}