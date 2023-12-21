package com.moralok.concurrency.ch2;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThinLockingToFatLockingTest {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        log.info("测试：当持有偏向锁的线程尚未离开同步块，其他线程尝试获取偏向锁时，将升级为重量级锁");

        log.info("sleep 4000ms，等待偏向锁激活");
        TimeUnit.SECONDS.sleep(4);

        Object lock = new Object();
        log.info("Mark Word 初始为 =====> 匿名偏向锁");
        log.info(ClassLayout.parseInstance(lock).toPrintable());

        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                log.info("第一个线程 {} 获取锁 =====> 偏向锁", Thread.currentThread().getName());
                log.info(ClassLayout.parseInstance(lock).toPrintable());

                log.info("暂停，输入任意字符回车继续");
                scanner.next();

                log.info("第一个线程 {} 持有偏向锁，在同步块内发生竞争 =====> 升级为重量级锁", Thread.currentThread().getName());
                log.info(ClassLayout.parseInstance(lock).toPrintable());
            }
            log.info("第一个线程 {} 结束", Thread.currentThread().getName());
        }, "thread1");
        thread1.start();

        TimeUnit.SECONDS.sleep(1);

        Thread thread2 = new Thread(() -> {
            log.info("第二个线程 {} 尝试获取偏向锁失败", Thread.currentThread().getName());
            synchronized (lock) {
                log.info("第二个线程 {} 获取锁 =====> 重量级锁", Thread.currentThread().getName());
                log.info(ClassLayout.parseInstance(lock).toPrintable());
            }
        }, "thread2");
        thread2.start();
        thread2.join();

        log.info("即使离开同步块后 =====> 重量级锁");
        log.info(ClassLayout.parseInstance(lock).toPrintable());
    }
}
