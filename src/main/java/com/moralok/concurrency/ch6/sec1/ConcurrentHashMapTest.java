package com.moralok.concurrency.ch6.sec1;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author moralok
 * @since 2021/3/8
 */
public class ConcurrentHashMapTest {

    /**
     * 普通HashMap多线程环境下使用put可能会引起死循环
     */
    private static Map<String, String> map = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                new Thread(() -> {
                    map.put(UUID.randomUUID().toString(), "");
                }, "ftf" + i).start();
            }
        }, "ftf");
        thread.start();
        thread.join();
    }
}
