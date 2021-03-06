package com.moralok.concurrency.ch9;

import java.util.concurrent.*;

/**
 * @author moralok
 * @since 2021/3/6
 */
public class ThreadPoolExecutorTest {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 50; i++) {
                int finalI = i;
                threadPoolExecutor.execute(() -> {
                    System.out.println("thread name:" + Thread.currentThread().getName()
                            + " job:" + finalI
                            + " active count:" + threadPoolExecutor.getActiveCount()
                            + " completed task:" + threadPoolExecutor.getCompletedTaskCount()
                            + " largest pool size:" + threadPoolExecutor.getLargestPoolSize()
                            + " pool size:" + threadPoolExecutor.getPoolSize()
                            + " task count:" + threadPoolExecutor.getTaskCount());
                    try {
                        TimeUnit.MICROSECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
            TimeUnit.SECONDS.sleep(5);
            // 当前线程数
            System.out.println("pool size " + threadPoolExecutor.getPoolSize());
            System.out.println("largest pool size " + threadPoolExecutor.getLargestPoolSize());
            // 主线程执行了一部分
            System.out.println("task count " + threadPoolExecutor.getTaskCount());
            System.out.println("completed task count " + threadPoolExecutor.getCompletedTaskCount());
            System.out.println("active count " + threadPoolExecutor.getActiveCount());
            System.out.println();
        }

        threadPoolExecutor.shutdown();
    }

}
