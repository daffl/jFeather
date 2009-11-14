package de.neyeon.feathry.cache;

import net.sf.ehcache.bootstrap.BootstrapCacheLoader;
import net.sf.ehcache.event.RegisteredEventListeners;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

class EhCacheConfiguration
{
		String name
		int maxElementsInMemory
		MemoryStoreEvictionPolicy memoryStoreEvictionPolicy = MemoryStoreEvictionPolicy.LRU
		boolean overflowToDisk
		String diskStorePath
		boolean eternal
		long timeToLiveSeconds
		long timeToIdleSeconds
		boolean diskPersistent
		long diskExpiryThreadIntervalSeconds
		RegisteredEventListeners registeredEventListeners
		BootstrapCacheLoader bootstrapCacheLoader
		int maxElementsOnDisk
		int diskSpoolBufferSizeMB
}