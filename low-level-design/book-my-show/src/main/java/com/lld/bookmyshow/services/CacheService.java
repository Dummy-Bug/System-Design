package com.lld.bookmyshow.services;

import org.springframework.stereotype.Service;

@Service
public interface CacheService {

    void set(String key, Object value);

    boolean setIfAbsent(String key, String value, long ttlSeconds);

    Object get(String key);

    void delete(String key);

    void getAllKeysAndValues();

    void deleteAll();
}