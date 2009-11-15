package de.neyeon.feathry.cache;

import net.sf.ehcache.Element;
import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import de.neyeon.feathry.logging.*;

class GrEhCacheService extends EhCacheService 
{
	/**
	 * Get an object with given id. Execute a closure if the object hasn't been found.
	 * The return value of the closure will be stored in the cache under the given key.
	 * @param id
	 * @param cl Closure to be executed if the object is not in the cache
	 * @return
	 */
	def get(def id, Closure cl)
	{
		return get(defaultCacheName, id, cl)
	}
	
	/**
	 * Get an object with given id. Execute a closure if the object hasn't been found.
	 * The return value of the closure will be stored in the cache under the given key. 
	 * @param regionName
	 * @param id
	 * @param cl Closure to be executed if the object is not in the cache
	 * @return
	 */
	def get(String regionName, def id, Closure cl)
	{
		def result = super.get(regionName, id)
		if(!result)
		{
			result = cl()
			super.put(regionName, id, result)
		}
		return result
	}
}