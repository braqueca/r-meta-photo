package com.braquec.rmetaphoto.cache;

public interface CacheLoaderService<K, V> {
    V getApiData(K key);
}
