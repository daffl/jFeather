package test.de.neyeon.feathry.cache;

import static org.junit.Assert.*;

import org.junit.Before;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.junit.Test;

import de.neyeon.feathry.ServiceFactory;
import de.neyeon.feathry.cache.CacheService;

class UserCacheServiceTest
{
	private UserCache userCache;
	
	@Before
	public void setUp()
	{
		userCache = ServiceFactory.getInstance().getUserCache()	
	}

	@Test
	public void testPutUserInCache()
	{
		
		def impl =
			[
				getUsername : { return "Testuser" },
				isEnabled : { return true }
			]
		userCache.putUserInCache(impl as UserDetails)
	}
	
	@Test
	public void testGetUserFromCache()
	{
		UserDetails details = userCache.getUserFromCache("Testuser")
		assert details != null
		assert details.getUsername() == "Testuser"
		assert details.isEnabled()
	}
	
	@Test
	public void testRemoveUserFromCache()
	{
		UserDetails details = userCache.getUserFromCache("Testuser")
		assert details != null
		userCache.removeUserFromCache("Testuser")
		assert userCache.getUserFromCache("Testuser") == null
	}
	
}