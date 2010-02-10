package test.org.feathry.cache;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.feathry.ServiceFactory;
import org.feathry.cache.CacheService;
import org.feathry.cache.EhCacheService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations=["/org/feathry/config/default.xml"])
class EhCacheServiceTest
{
	@Autowired
	CacheService cache
	
	@Test
	public void testEhCacheService()
	{
		assert cache != null
	}

	@Test
	public void testGetObject()
	{
		cache.put("Test", "Hello world")
		assert cache.get("Test") == "Hello world"
	}

	@Test
	public void testGetStringObject()
	{
		cache.put("feathry.users", "Test", "Hello world")
		assert cache.get("feathry.users", "Test") == "Hello world"
	}

	@Test
	public void testPutStringObjectObject()
	{
		cache.put("feathry.users", "Test", "Hello world")
		assert cache.manager.getCache("feathry.users").getSize() == 1
	}

	@Test
	public void testRemoveObject()
	{
		cache.put("Test", "Hello world")
		cache.remove("Test")
		assert cache.get("Test") == null
	}
	
	@Test
	public void testGetStringObjectClosure()
	{
		assert cache.get("Test") == null
		def res = cache.get(cache.defaultCacheName, "Test") {
			def str1 = "Hello "
			def str2 = "world"
			return str1 + str2
		}
		assert res == "Hello world"
		assert cache.get("Test") == "Hello world" 
	}
	
	public void testExists()
	{
		assert cache.exists("Test");
	}
}