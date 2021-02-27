package com.moralok.ch4;

import java.util.concurrent.TimeUnit;

/**
 * @author moralok
 * @since 2021/2/28
 */
public class Shutdown {

    /**
     * 安全地中断线程
     * 
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread countThread = new Thread(new ShutdownRunner(), "CountThread");
        countThread.start();
        TimeUnit.SECONDS.sleep(1);
        countThread.interrupt();
        ShutdownRunner runner = new ShutdownRunner();
        countThread = new Thread(runner, "CountThread");
        countThread.start();
        TimeUnit.SECONDS.sleep(1);
        runner.cancel();
    }

    static class ShutdownRunner implements Runnable {

        private volatile boolean on = true;

        private long i;

        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println("Count i = " + i);
        }

        public void cancel() {
            on = false;
        }
    }
}
