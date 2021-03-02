package com.moralok.concurrency.ch5.sec6;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author moralok
 * @since 2021/3/2
 */
public class BoundQueue<T> {

    private int addIndex, removeIndex, count;
    private T[] items;
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    @SuppressWarnings("unchecked")
    public BoundQueue(int size) {
        items = (T[]) new Object[size];
    }

    public void add(T t) {
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();
            }
            items[addIndex] = t;
            if (++addIndex == items.length) {
                addIndex = 0;
            }
            count++;
            notFull.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T remove() {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            T x = items[removeIndex];
            if (++removeIndex == items.length) {
                removeIndex = 0;
            }
            count--;
            notFull.signal();
            return x;
        } catch (InterruptedException e) {

        } finally {
            lock.unlock();
        }
        return null;
    }

    public static void main(String[] args) throws InterruptedException {
        BoundQueue<Integer> integerBoundQueue = new BoundQueue<>(2);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                integerBoundQueue.add(i);
                System.out.println("t1 add " + i);
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            Integer remove = integerBoundQueue.remove();
            System.out.println("remove " + remove);
        }, "t2");
        t1.start();
        System.out.println("sleep 3s");
        TimeUnit.SECONDS.sleep(3);
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(Arrays.toString(integerBoundQueue.items));
    }
}
