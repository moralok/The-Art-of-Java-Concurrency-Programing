package com.moralok.concurrency.ch8.sec2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author moralok
 * @since 2021/3/6
 */
public class CyclicBarrierTest3 {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(1);
        });
        thread.start();
        thread.interrupt();
        try {
            cyclicBarrier.await();
        } catch (BrokenBarrierException e) {
            System.out.println(cyclicBarrier.isBroken());
        }
        System.out.println(2);
    }

    private static class Job implements Runnable {

        @Override
        public void run() {
            System.out.println(3);
        }
    }
}
