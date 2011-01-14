package test.org.feathry.cache;

import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations=["/org/feathry/config/default.xml"])
class UserCacheImplTest
{
	@Autowired
	UserCache userCache;

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