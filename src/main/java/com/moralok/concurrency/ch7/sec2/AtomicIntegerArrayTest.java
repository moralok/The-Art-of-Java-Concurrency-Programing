package com.moralok.concurrency.ch7.sec2;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author moralok
 * @since 2021/3/7
 */
public class AtomicIntegerArrayTest {

    private static int[] values = new int[] {1, 2};

    private static AtomicIntegerArray aia = new AtomicIntegerArray(values);

    public static void main(String[] args) {
        aia.getAndSet(0, 3);
        System.out.println(aia.get(0));
        // 不影响传入的数组
        System.out.println(values[0]);
    }
}
