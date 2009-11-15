package test.de.neyeon.feathry.cache;

import static org.junit.Assert.*;

import org.junit.Before;
import de.neyeon.feathry.cache.GrEhCacheService;

import org.junit.Test;

import de.neyeon.feathry.ServiceFactory;
import de.neyeon.feathry.cache.CacheService;
import de.neyeon.feathry.cache.EhCacheService;

class GrEhCacheServiceTest
{
	private GrEhCacheService cache;
	
	@Before
	public void setUp()
	{
		CacheService cs = ServiceFactory.getInstance().getCache();
		if(cs instanceof GrEhCacheService)
			cache = (GrEhCacheService)cs;
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