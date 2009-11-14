package de.neyeon.feathry.cache;

import net.sf.ehcache.Element;
import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

class EhCacheService implements CacheService 
{
	protected CacheManager manager
	protected String defaultCacheName
	protected String userCacheName
	
	EhCacheService() {
		manager = CacheManager.create()
	}
	
	String getDefaultCacheName()
	{
		return defaultCache
	}
	
	String getUserCacheName()
	{
		return userCache
	}
	
	void setDefaultCacheConfiguration(EhCacheConfiguration config)
	{
		this.addCache(config)
		defaultCache = config.name
	}
	
	void setUserCacheConfiguration(EhCacheConfiguration config)
	{
		this.addCache(config)
		userCache = config.name
	}
	
	void addCache(EhCacheConfiguration config)
	{
		Cache cache = new Cache(
				config.name,
				config.maxElementsInMemory,
				config.memoryStoreEvictionPolicy,
				config.overflowToDisk,
				config.diskStorePath,
				config.eternal,
				config.timeToLiveSeconds,
				config.timeToIdleSeconds,
				config.diskPersistent,
				config.diskExpiryThreadIntervalSeconds,
				config.getRegisteredEventListeners()
			)
		manager.addCache(cache)
	}
	
	Cache defaultCache()
	{
		return manager.getCache(defaultCacheName)
	}
	
	def get(def id)
	{
		Element elem = (Element)this.defaultCache().get(id)
		elem.getObjectValue()
	}
	
	def get(String regionName, def id)
	{
		manager.getCache(regionName).get(id)
	}
	
	void put(def id, def obj)
	{
		this.defaultCache().put(new Element(id, obj));
	}
	
	void put(String regionName, def id, def obj)
	{
		manager.getCache(regionName).put(new Element(id, obj))
	}
	
	void remove(def id)
	{
		defaultCache().remove(id)
	}
	
	void remove(String regionName, def id)
	{
		manager.getCache(regionName).remove(id)
	}
	
	public void shutdown() {
		manager.shutdown()
	}
	
	protected void finalize() throws Throwable 
	{
		this.shutdown()
	}
}