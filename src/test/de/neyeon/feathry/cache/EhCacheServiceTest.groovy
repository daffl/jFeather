package test.de.neyeon.feathry.cache;

import net.sf.ehcache.Cache;
import org.junit.Before;
import org.junit.Test;

import de.neyeon.feathry.ServiceFactory;
import de.neyeon.feathry.cache.CacheService;
import de.neyeon.feathry.cache.EhCacheService;

class EhCacheServiceTest
{
	private EhCacheService cache;
	
	@Before
	public void setUp()
	{
		CacheService cs = ServiceFactory.getInstance().getCache();
		if(cs instanceof EhCacheService)
			cache = (EhCacheService)cs;
	}
	
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
		assert cache.userCacheName != null
		cache.put(cache.userCacheName, "Test", "Hello world")
		assert cache.get(cache.userCacheName, "Test") == "Hello world"
	}

	@Test
	public void testPutStringObjectObject()
	{
		cache.put(cache.userCacheName, "Test", "Hello world")
		assert cache.manager.getCache(cache.defaultCacheName).getSize() == 1
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
}