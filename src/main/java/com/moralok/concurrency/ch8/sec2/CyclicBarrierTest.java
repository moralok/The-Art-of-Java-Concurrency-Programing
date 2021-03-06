package com.moralok.concurrency.ch8.sec2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author moralok
 * @since 2021/3/6
 */
public class CyclicBarrierTest {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(1);
        }).start();
        TimeUnit.SECONDS.sleep(2);
        try {
            cyclicBarrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(2);
    }
}
