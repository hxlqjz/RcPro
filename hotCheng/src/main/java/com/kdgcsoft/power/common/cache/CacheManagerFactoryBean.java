package com.kdgcsoft.power.common.cache;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.jcs.engine.control.CompositeCacheManager;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.config.ConfigurationException;
import org.apache.shiro.io.ResourceUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheFactoryBean;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

public class CacheManagerFactoryBean implements FactoryBean<CacheManager>,
		InitializingBean, DisposableBean {

	@Value("${cache.type}")
	private String cacheType;

	@Autowired(required = false)
	private RedisTemplate<?, ?> redisTemplate;
	private CacheManager cacheManager;
	private String configFile;

	public void afterPropertiesSet() throws Exception {
		switch (CacheTypeEnum.valueOf(cacheType.trim().toUpperCase())) {
		case SIMPLE:
			this.cacheManager = createSimpleCacheManager();
			break;
		case REDIS:
			this.cacheManager = createRedisCacheManager();
			break;
		case EHCACHE:
			configFile = "classpath:cache/ehcache/ehcache.xml";
			this.cacheManager = createEhcaheCacheManager();
			break;
		case JCS:
			configFile = "classpath:jcs/cache.ccf";
			this.cacheManager = createJCSCacheManager();
			break;
		default:
			throw new IllegalArgumentException(
					"cacheType值不正确，请确认在properties文件中配置cache.type，可选值为：simple,redis 或 ehcache");
		}
	}

	private CacheManager createSimpleCacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		String[] names = { CacheNames.MENU, CacheNames.DICTIONARY };
		List<Cache> caches = new ArrayList<Cache>();
		for (String cacheName : names) {
			ConcurrentMapCacheFactoryBean factoryBean = new ConcurrentMapCacheFactoryBean();
			factoryBean.setName(cacheName);
			factoryBean.afterPropertiesSet();

			caches.add(factoryBean.getObject());
		}

		cacheManager.setCaches(caches);
		cacheManager.afterPropertiesSet();

		return cacheManager;
	}

	private CacheManager createRedisCacheManager() {
		if (this.redisTemplate == null) {
			throw new IllegalArgumentException(
					"redisTemplate bean 没有配置, 无法使用redis cache");
		}
		return new RedisCacheManager(this.redisTemplate);
	}

	private CacheManager createEhcaheCacheManager() {
		try {
			return new EhCacheCacheManager(new net.sf.ehcache.CacheManager(
					getCacheManagerConfigFileInputStream()));
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	private InputStream getCacheManagerConfigFileInputStream() {
		try {
			return ResourceUtils.getInputStreamForPath(configFile);
		} catch (IOException e) {
			throw new ConfigurationException(
					"Unable to obtain input stream for cacheManagerConfigFile ["
							+ configFile + "]", e);
		}
	}

	protected CacheManager createJCSCacheManager() {
		CompositeCacheManager cacheManager;
		try {
			Properties configProperties = getConfigProperties();
			cacheManager = CompositeCacheManager.getUnconfiguredInstance();
			cacheManager.configure(configProperties);
			return new JCSCacheManager(cacheManager);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new CacheException(e);
		}
	}

	private Properties getConfigProperties() throws IOException {
		Properties properties = null;
		properties = new Properties();
		properties.load(getCacheManagerConfigFileInputStream());
		return properties;
	}

	public CacheManager getObject() throws Exception {
		return this.cacheManager;
	}

	public Class<CacheManager> getObjectType() {
		return CacheManager.class;
	}

	public boolean isSingleton() {
		return true;
	}

	@Override
	public void destroy() throws Exception {
		if (this.cacheManager != null) {
			if (this.cacheManager instanceof net.sf.ehcache.CacheManager) {
				((net.sf.ehcache.CacheManager) this.cacheManager).shutdown();
			}
			if (this.cacheManager instanceof CompositeCacheManager) {
				((CompositeCacheManager) this.cacheManager).shutDown();
			}
		}

	}

}