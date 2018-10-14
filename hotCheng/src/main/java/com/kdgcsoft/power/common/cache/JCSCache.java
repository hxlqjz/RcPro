package com.kdgcsoft.power.common.cache;

import java.io.IOException;
import java.io.Serializable;

import org.apache.jcs.engine.CacheElement;
import org.apache.jcs.engine.behavior.ICacheElement;
import org.apache.jcs.engine.control.CompositeCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

public class JCSCache implements Cache{

	private static final Logger LOGGER = LoggerFactory.getLogger(JCSCache.class);
	
	private final CompositeCache cache;
	
	public JCSCache(CompositeCache cache) {
		this.cache = cache;
	}


	@Override
	public final String getName() {
		return this.cache.getCacheName();
	}

	@Override
	public final CompositeCache getNativeCache() {
		return this.cache;
	}

	@Override
	public ValueWrapper get(Object key) {
		ICacheElement element = this.cache.get((Serializable)key);
		return (element != null ? new SimpleValueWrapper(element.getVal()) : null);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T get(Object key, Class<T> type) {
		ICacheElement element = this.cache.get((Serializable)key);
		Object value = (element != null ? element.getVal() : null);
		if (type != null && !type.isInstance(value)) {
			throw new IllegalStateException("Cached value is not of required type [" + type.getName() + "]: " + value);
		}
		return (T) value;
	}

	@Override
	public void put(Object key, Object value) {
		 try {
			CacheElement ce = new CacheElement(this.cache.getCacheName(), (Serializable)key, (Serializable)value);
			ce.setElementAttributes(this.cache.getElementAttributes());
		    this.cache.update(ce);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(),e);
		}
	}

	@Override
	public void evict(Object key) {
		this.cache.remove((Serializable)key);
	}

	@Override
	public void clear() {
		try {
			this.cache.removeAll();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage(),e);
		}
	}


}
