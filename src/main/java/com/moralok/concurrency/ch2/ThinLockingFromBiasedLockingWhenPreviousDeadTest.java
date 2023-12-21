package com.moralok.concurrency.ch2;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThinLockingFromBiasedLockingWhenPreviousDeadTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        log.info("测试：之前获得偏向锁的线程已死时，新线程获得的仍然是偏向锁");

        log.info("sleep 4000ms，等待偏向锁激活");
        TimeUnit.MILLISECONDS.sleep(4000);

        Object lock = new Object();
        log.info("Mark Word 初始为 =====> 匿名偏向锁");
        log.info(ClassLayout.parseInstance(lock).toPrintable());

        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                log.info("第一个线程 {} 获取锁 =====> 偏向锁", Thread.currentThread().getName());
                log.info(ClassLayout.parseInstance(lock).toPrintable());
            }
        }, "thread1");
        thread1.start();

        Thread thread2 = new Thread(() -> {
            try {
                thread1.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            boolean alive = thread1.isAlive();
            log.info("第一个线程 {} 是否存活 {}", thread1.getName(), alive);
            log.info(ClassLayout.parseInstance(lock).toPrintable());
            synchronized (lock) {
                log.info("即使第一个线程已死亡，第二个线程 {} 获取锁 =====> 轻量级锁", Thread.currentThread().getName());
                log.info(ClassLayout.parseInstance(lock).toPrintable());
            }
        }, "thread2");
        thread2.start();
        thread2.join();

        log.info("离开同步块后轻量级锁释放 =====> 无锁状态（可偏向的）");
        log.info(ClassLayout.parseInstance(lock).toPrintable());
    }
}
