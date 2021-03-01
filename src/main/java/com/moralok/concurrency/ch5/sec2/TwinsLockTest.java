package com.moralok.concurrency.ch5.sec2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author moralok
 * @since 2021/3/1 5:54 下午
 */
public class TwinsLockTest {

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new TwinsLock();
        for (int i = 0; i < 10; i++) {
            Worker worker = new Worker(lock);
            worker.setDaemon(true);
            worker.start();
        }
        for (int i = 0; i < 20; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println();
        }
    }

    static class Worker extends Thread {

        private final Lock lock;

        Worker(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName());
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {

                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
