package de.neyeon.feathry.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;


class UserCacheImpl implements UserCache
{
	@Autowired
	CacheService cache
	String userCacheName
	
	UserDetails getUserFromCache(String username)
	{
		return cache.get(userCacheName, username)
	}
	
	void putUserInCache(UserDetails user)
	{		
		cache.put(userCacheName, user.getUsername(), user)
	}
	
	void removeUserFromCache(String username)
	{
		cache.remove(userCacheName, username)
	}
}