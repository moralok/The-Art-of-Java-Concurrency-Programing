package com.moralok.concurrency.ch2;

public class SynchronizedBaseTest {

    private static Object lock = new Object();

    public static void main(String[] args) {
        int i = 0;
        synchronized (lock) {
            i++;
        }
    }

    public static synchronized void lock() {
    }
}
