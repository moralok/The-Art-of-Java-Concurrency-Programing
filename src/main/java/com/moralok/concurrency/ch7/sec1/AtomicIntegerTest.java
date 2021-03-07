package com.moralok.concurrency.ch7.sec1;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author moralok
 * @since 2021/3/7
 */
public class AtomicIntegerTest {

    private static AtomicInteger ai = new AtomicInteger(1);

    public static void main(String[] args) {
        System.out.println(ai.getAndIncrement());
        System.out.println(ai.get());
    }
}
