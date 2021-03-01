package com.moralok.concurrency.ch5.sec4;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author moralok
 * @since 2021/3/1 6:54 下午
 */
public class Cache {

    private static Map<String, Object> map = new HashMap<>();
    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static Lock r = lock.readLock();
    private static Lock w = lock.writeLock();

    public static final Object get(String key) {
        r.lock();
        try {
            return map.get(key);
        } finally {
            r.unlock();
        }
    }

    public static final Object put(String key, Object value) {
        w.lock();
        try {
            return map.put(key, value);
        } finally {
            w.unlock();
        }
    }

    public static final void clear() {
        w.lock();
        try {
            map.clear();
        } finally {
            w.unlock();
        }
    }

    public static final int size() {
        r.lock();
        try {
            return map.size();
        } finally {
            r.unlock();
        }
    }
}
