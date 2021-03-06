package com.moralok.concurrency.ch8.sec1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author moralok
 * @since 2021/3/6
 */
public class CountDownLatchTest {

    private static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                countDownLatch.countDown();
                System.out.println(1);
                TimeUnit.SECONDS.sleep(2);
                countDownLatch.countDown();
                System.out.println(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        countDownLatch.await();
        System.out.println(3);
    }
}
