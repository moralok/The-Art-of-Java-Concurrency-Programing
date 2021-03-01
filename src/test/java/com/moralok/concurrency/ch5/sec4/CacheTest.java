package com.moralok.concurrency.ch5.sec4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author moralok
 * @since 2021/3/1 6:58 下午
 */
class CacheTest {

    @Test
    void get() {
        Object a = Cache.get("a");
        Assertions.assertNull(a);
    }

    @Test
    void put() {
        Cache.put("b", 2);
        Object b = Cache.get("b");
        Assertions.assertEquals(2, (int) b);
    }

    @Test
    void clear() {
        Cache.clear();
        Cache.put("c", 3);
        Assertions.assertEquals(1, Cache.size());
        Cache.clear();
        Assertions.assertEquals(0, Cache.size());
    }
}
