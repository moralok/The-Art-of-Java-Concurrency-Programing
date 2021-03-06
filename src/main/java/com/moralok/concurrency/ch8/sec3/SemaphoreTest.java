package com.moralok.concurrency.ch8.sec3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author moralok
 * @since 2021/3/6
 */
public class SemaphoreTest {

    private static final int THREAD_COUNT = 40;
    private static ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);
    private static Semaphore s = new Semaphore(10);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            pool.execute(() -> {
                try {
                    s.acquire();
                    System.out.println("save data " + s.availablePermits() + " " + s.getQueueLength());
                    TimeUnit.SECONDS.sleep(2);
                    s.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        pool.shutdown();
    }
}
