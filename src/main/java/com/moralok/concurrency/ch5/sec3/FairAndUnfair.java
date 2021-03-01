package com.moralok.concurrency.ch5.sec3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author moralok
 * @since 2021/3/1 6:24 下午
 */
public class FairAndUnfair {

    static Lock fairLock = new ReentrantLock2(true);
    static Lock unfairLock = new ReentrantLock2(false);

    public static void testLock(Lock lock) {
        for (int i = 0; i < 5; i++) {
            new Job(lock, String.valueOf(i)).start();
        }
    }

    static class ReentrantLock2 extends ReentrantLock {
        public ReentrantLock2(boolean fair) {
            super(fair);
        }

        @Override
        protected Collection<Thread> getQueuedThreads() {
            List<Thread> arrayList = new ArrayList<>(super.getQueuedThreads());
            Collections.reverse(arrayList);
            return arrayList;
        }
    }

    static class Job extends Thread {

        private Lock lock;

        public Job(Lock lock, String name) {
            super(name);
            this.lock = lock;
        }

        @Override
        public void run() {
            for (int i = 0; i < 2; i++) {
                lock.lock();
                try {
                    System.out.println("Lock by [" + Thread.currentThread().getName() + "], Waiting by " + ((ReentrantLock2)lock).getQueuedThreads());
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
