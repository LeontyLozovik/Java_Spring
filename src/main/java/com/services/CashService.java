package com.services;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CashService {
    private static Map<InitialServise, String> cache = new HashMap<>();

    public boolean hasKey(InitialServise key) {
        return cache.containsKey(key);
    }

    public String get(InitialServise key) {

        return cache.get(key);
    }

    public void put(InitialServise key, String result) {
        cache.put(key, result);
    }
}
