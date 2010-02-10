package org.feathry.cache;

import groovy.lang.Closure;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhCacheService implements CacheService
{
	protected CacheManager manager;
	protected String defaultCacheName;

	public EhCacheService(String resourceName)
	{
		manager = new CacheManager(getClass().getClassLoader().getResourceAsStream(resourceName));
	}

	@Override
	public Object get(Object id)
	{
		return get(getDefaultCacheName(), id);
	}
	
	/**
	 * Get an object with given id. Execute a closure if the object hasn't been found.
	 * The return value of the closure will be stored in the cache under the given key.
	 * @param id
	 * @param cl
	 * @return
	 */
	public Object get(Object id, Closure cl)
	{
		return get(getDefaultCacheName(), id, cl);
	}
	
	/**
	 * Get an object with given id. Execute a closure if the object hasn't been found.
	 * The return value of the closure will be stored in the cache under the given key. 
	 * @param regionName
	 * @param id
	 * @param cl
	 * @return
	 */
	public Object get(String regionName, Object id, Closure cl)
	{
		Object result = get(regionName, id);
		if(result == null)
		{
			result = cl.call();
			put(regionName, id, result);
		}		
		return result;	
	}

	@Override
	public Object get(String regionName, Object id)
	{
		Element res = manager.getCache(regionName).get(id);
		if(res == null)
			return null;
		return res.getObjectValue();
	}

	@Override
	public void put(Object id, Object obj)
	{
		put(getDefaultCacheName(), id, obj);
	}

	@Override
	public void put(String regionName, Object id, Object obj)
	{
		manager.getCache(regionName).put(new Element(id, obj));
	}

	@Override
	public void remove(Object id)
	{
		remove(getDefaultCacheName(), id);
	}

	@Override
	public void remove(String regionName, Object id)
	{
		manager.getCache(regionName).remove(id);
	}
	
	@Override
	protected void finalize() throws Throwable
	{
		super.finalize();
		shutdown();
	}
	
	public void shutdown()
	{
		manager.shutdown();
	}
	
	public String getDefaultCacheName()
	{
		return defaultCacheName;
	}

	public void setDefaultCacheName(String defaultCacheName)
	{
		this.defaultCacheName = defaultCacheName;
	}

	public CacheManager getManager()
	{
		return manager;
	}

	@Override
	public boolean exists(Object id)
	{
		return exists(defaultCacheName, id);
	}

	@Override
	public boolean exists(String regionName, Object key)
	{
		return manager.getCache(regionName).isKeyInCache(key);
	}	
}
