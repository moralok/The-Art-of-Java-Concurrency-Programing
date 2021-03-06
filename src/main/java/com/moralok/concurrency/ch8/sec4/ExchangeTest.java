package com.moralok.concurrency.ch8.sec4;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author moralok
 * @since 2021/3/6
 */
public class ExchangeTest {

    private static Exchanger<String> exchanger = new Exchanger<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        pool.submit(() -> {
            try {
                String A = "银行流水A";
                String B = exchanger.exchange(A);
                System.out.println("交换的数据: " + B);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        pool.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                String B = "银行流水B";
                String A = exchanger.exchange(B);
                System.out.println("A和B数据一致: " + A.equals(B) + " A录入的是: " + A + " B录入的是: " + B);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        pool.shutdown();
    }
}
