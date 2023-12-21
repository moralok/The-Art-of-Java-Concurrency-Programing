package com.moralok.concurrency.ch2;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

@Slf4j
public class BiasedLockingDisabledTest {

    // JVM 参数设置为 -XX:-UseBiasedLocking
    public static void main(String[] args) throws InterruptedException {
        log.info("测试：关闭偏向锁");

        Object lock = new Object();
        log.info("Mark Word 初始为 =====> 无锁状态（非可偏向的）");
        log.info(ClassLayout.parseInstance(lock).toPrintable());

        log.info("sleep 4000ms");
        TimeUnit.MILLISECONDS.sleep(4000);

        log.info("即使过了偏向延迟时间，创建的对象的对象头的 Mark Word 仍然是 =====> 无锁状态（非可偏向的）");
        log.info(ClassLayout.parseInstance(lock).toPrintable());
    }
}
