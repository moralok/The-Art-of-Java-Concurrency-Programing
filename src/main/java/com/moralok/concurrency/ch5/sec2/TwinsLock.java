package com.moralok.concurrency.ch5.sec2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author moralok
 * @since 2021/3/1 5:40 下午
 */
public class TwinsLock implements Lock {

    private final Sync sync = new Sync(2);

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    private static class Sync extends AbstractQueuedSynchronizer {

        Sync(int count) {
            if (count < 0) {
                throw new IllegalArgumentException();
            }
            setState(count);
        }

        @Override
        protected int tryAcquireShared(int arg) {
            for (;;) {
                int state = getState();
                int newCount = state - arg;
                if (newCount < 0 || compareAndSetState(state, newCount)) {
                    return newCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            for (;;) {
                int state = getState();
                int newCount = state + arg;
                if (compareAndSetState(state, newCount)) {
                    return true;
                }
            }
        }
    }
}
