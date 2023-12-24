package com.moralok.concurrency.ch2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

    private AtomicInteger atomicCnt = new AtomicInteger(0);
    private int cnt = 0;

    public static void main(String[] args) {
        Counter counter = new Counter();
        List<Thread> threads = new ArrayList<>(100);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    counter.safeCount();
                    counter.count();
                }
            });
            threads.add(thread);
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 线程安全
        System.out.println(counter.atomicCnt.get());
        // 线程不安全
        System.out.println(counter.cnt);
        System.out.println(System.currentTimeMillis() - start);
    }

    private void safeCount() {
        for (;;) {
            int integer = atomicCnt.get();
            boolean suc = atomicCnt.compareAndSet(integer, ++integer);
            if (suc) {
                break;
            }
        }
    }

    private void count() {
        cnt++;
    }
}
