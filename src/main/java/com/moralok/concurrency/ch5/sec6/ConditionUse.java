package com.moralok.concurrency.ch5.sec6;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author moralok
 * @since 2021/3/2
 */
public class ConditionUse {

    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private static volatile boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        Thread waitThread = new Thread(new ConditionWait(), "waitThread");
        Thread signalThread = new Thread(new ConditionSignal(), "signalThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("sleep 3s");
        signalThread.start();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("sleep 3s again");
        flag = false;
    }

    static class ConditionWait implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                while (flag) {

                }
                System.out.println("wait start");
                condition.await();
                System.out.println("wait end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    static class ConditionSignal implements Runnable {

        @Override
        public void run() {
            lock.lock();
            System.out.println("signal lock");
            try {
                TimeUnit.SECONDS.sleep(3);
                condition.signal();
                System.out.println("signal signal");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("signal unlock");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
