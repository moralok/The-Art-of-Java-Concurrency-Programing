package com.moralok.ch4;

import java.util.concurrent.TimeUnit;

/**
 * @author moralok
 * @since 2021/2/28
 */
public class Daemon {

    public static void main(String[] args) {
        Thread daemonRunner = new Thread(new DaemonRunner(), "DaemonRunner");
        daemonRunner.setDaemon(true);
        daemonRunner.start();
        System.out.println("main ends");
    }

    static class DaemonRunner implements Runnable {

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {

            } finally {
                // daemon thread finally不一定执行
                System.out.println("Daemon Runner finally run");
            }
        }
    }
}
