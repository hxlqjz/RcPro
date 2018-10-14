package com.kdgcsoft.power.common.cache;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.apache.jcs.engine.control.CompositeCache;
import org.apache.jcs.engine.control.CompositeCacheManager;
import org.springframework.cache.Cache;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;

public class JCSCacheManager extends
		AbstractTransactionSupportingCacheManager {

	private CompositeCacheManager cacheManager;
	
	
	public JCSCacheManager(CompositeCacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public CompositeCacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CompositeCacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Override
	protected Collection<? extends Cache> loadCaches() {
		CompositeCacheManager cacheManager = getCacheManager();
		String[] names = cacheManager.getCacheNames();
		Collection<Cache> caches = new LinkedHashSet<Cache>(names.length);
		for (String name : names) {
			caches.add(new JCSCache(cacheManager.getCache(name)));
		}
		return caches;
	}
	
	@Override
	public Cache getCache(String name) {
		Cache cache = super.getCache(name);
		if (cache == null) {
			CompositeCache jscache = getCacheManager().getCache(name);
			if (jscache != null) {
				addCache(new JCSCache(jscache));
				cache = super.getCache(name);  // potentially decorated
			}
		}
		return cache;
	}
}
