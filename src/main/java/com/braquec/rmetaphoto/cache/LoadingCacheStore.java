package com.braquec.rmetaphoto.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class LoadingCacheStore<K, V> {
    private LoadingCache<K, V> loadingCache;

    public LoadingCacheStore(int expiryDuration, TimeUnit timeUnit, CacheLoaderService<K, V> service) {
        CacheLoader<K, V> loader = new CacheLoader<>() {
            @Override
            public V load(K key) {
                return service.getApiData(key);
            }
        };

        loadingCache = CacheBuilder.newBuilder()
                .expireAfterWrite(expiryDuration, timeUnit)
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .build(loader);
    }

    public V get(K key) throws ExecutionException {
        return loadingCache.get(key);
    }

}
