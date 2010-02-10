package org.feathry.cache;

/**
 * Interface for a simple caching service.<br />
 * Cache regions are defined by a name and split the cache into subregions.
 * If not given, objects will be stored in the default region.
 * @author daff
 *
 */
public interface CacheService
{
	/**
	 * Get an object with given id from the default cache.
	 * @param id The key of the object stored in the cache.
	 * @return The stored object, <code>null</code> if the object was not fount
	 */
	public Object get(Object id);	
	/**
	 * Get an object with given id from a cache region. 
	 * @param regionName Name of the cache region
	 * @param id The key of the object stored in the cache.
	 * @return The stored object, <code>null</code> if the object was not fount
	 */
	public Object get(String regionName, Object id);
	/**
	 * Put an object into the cache.
	 * @param id The key to store this object at in the cache.
	 * @param obj
	 */
	public void put(Object id, Object obj);
	/**
	 * Put an object into the cache. 
	 * @param regionName
	 * @param id The key to store this object at in the cache.
	 * @param obj
	 */
	public void put(String regionName, Object id, Object obj);
	/**
	 * Remove an object from the cache.
	 * @param id
	 */
	public void remove(Object id);
	public void remove(String regionName, Object id);
	public boolean exists(Object id);	
	public boolean exists(String regionName, Object key);
}
