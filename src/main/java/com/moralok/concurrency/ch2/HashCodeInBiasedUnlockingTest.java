package com.moralok.concurrency.ch2;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

@Slf4j
public class HashCodeInBiasedUnlockingTest {

    public static void main(String[] args) throws InterruptedException {
        log.info("测试：在偏向锁状态无锁时计算 hashCode");

        log.info("sleep 4000ms，等待偏向锁激活");
        TimeUnit.MILLISECONDS.sleep(4000);

        Object lock = new Object();
        log.info("Mark Word 初始为 =====> 匿名偏向锁");
        log.info(ClassLayout.parseInstance(lock).toPrintable());

        synchronized (lock) {
            log.info("获取锁 =====> 偏向锁");
            log.info(ClassLayout.parseInstance(lock).toPrintable());
        }

        int hashCode = lock.hashCode();
        log.info("离开同步块后再计算 hashCode：Mark Word =====> 无锁状态");
        log.info(ClassLayout.parseInstance(lock).toPrintable());
    }
}
