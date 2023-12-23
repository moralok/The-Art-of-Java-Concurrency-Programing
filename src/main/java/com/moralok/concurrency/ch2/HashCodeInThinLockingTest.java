package com.moralok.concurrency.ch2;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

@Slf4j
public class HashCodeInThinLockingTest {

    public static void main(String[] args) throws InterruptedException {
        log.info("测试：在轻量级锁状态计算 hashCode");

        Object lock = new Object();
        log.info("Mark Word 初始为 =====> 无锁状态");
        log.info(ClassLayout.parseInstance(lock).toPrintable());

        synchronized (lock) {
            log.info("获取锁 =====> 轻量级锁");
            log.info(ClassLayout.parseInstance(lock).toPrintable());

            int hashCode = lock.hashCode();
            log.info("在计算 hashCode 后：Mark Word =====> 重量级锁");
            log.info(ClassLayout.parseInstance(lock).toPrintable());
        }

        log.info("即使离开同步块后 =====> 重量级锁");
        log.info(ClassLayout.parseInstance(lock).toPrintable());
    }
}
