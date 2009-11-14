package de.neyeon.feathry.cache;

public interface CacheService
{
	/**
	 * Get an object with given id from the default cache.
	 * @param id The id of the object stored in the cache.
	 * @return The stored object, <code>null</code> if the object was not fount
	 */
	public Object get(Object id);
	public Object get(String regionName, Object id);
	public void put(Object id, Object obj);
	public void put(String regionName, Object id, Object obj);
	public void remove(Object id);
	public void remove(String regionName, Object id);
}
