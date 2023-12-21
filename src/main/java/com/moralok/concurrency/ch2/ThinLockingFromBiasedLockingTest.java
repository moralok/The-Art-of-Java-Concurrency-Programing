package com.moralok.concurrency.ch2;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ThinLockingFromBiasedLockingTest {

    public static void main(String[] args) throws InterruptedException {
        log.info("测试：当持有偏向锁的线程已经离开同步块，其他线程尝试获取偏向锁时，将获得轻量级锁");

        log.info("sleep 4000ms，等待偏向锁激活");
        TimeUnit.SECONDS.sleep(4);

        Object lock = new Object();
        log.info("Mark Word 初始为 =====> 匿名偏向锁");
        log.info(ClassLayout.parseInstance(lock).toPrintable());

        synchronized (lock) {
            log.info("第一个线程 {} 获取锁 =====> 偏向锁", Thread.currentThread().getName());
            log.info(ClassLayout.parseInstance(lock).toPrintable());
        }

        Thread thread = new Thread(() -> {
            log.info("第二个线程 {} 尝试获取锁", Thread.currentThread().getName());
            log.info(ClassLayout.parseInstance(lock).toPrintable());
            synchronized (lock) {
                log.info("第二个线程 {} 获取锁 =====> 轻量级锁", Thread.currentThread().getName());
                log.info(ClassLayout.parseInstance(lock).toPrintable());
            }
        }, "thread1");
        thread.start();
        thread.join();

        log.info("离开同步块后轻量级锁释放 =====> 无锁状态（可偏向的）");
        log.info(ClassLayout.parseInstance(lock).toPrintable());
    }
}
