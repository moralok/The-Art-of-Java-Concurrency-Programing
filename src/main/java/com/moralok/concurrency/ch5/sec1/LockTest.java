package com.moralok.concurrency.ch5.sec1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author moralok
 * @since 2021/3/1 5:01 下午
 */
public class LockTest {

    private static Lock lock = new ReentrantLock();
    private static CountDownLatch start = new CountDownLatch(1);
    private static volatile boolean flag = false;

    public static void main(String[] args) {
        new Thread(new LockRunner(), "t-1").start();
        new Thread(new LockRunner(), "t-2").start();
        flag = true;
        start.countDown();
    }

    static class LockRunner implements Runnable {

        @Override
        public void run() {
            while (!flag) {
                try {
                    start.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lock.lock();
            try {
                doSomething();
            } finally {
                lock.unlock();
            }
        }

        private static void doSomething() {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
