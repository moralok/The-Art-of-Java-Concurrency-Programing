package com.moralok.concurrency.ch2;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class BiasedLockingStartupDelayTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        Object lock = new Object();
        // 无锁状态
        log.info(ClassLayout.parseInstance(lock).toPrintable());
        // 默认情况下偏向延迟的设置为 -XX:BiasedLockingStartupDelay=4000
        TimeUnit.SECONDS.sleep(4);
        // 之后创建的对象的对象头默认是没有设置线程 ID 的偏向锁状态
        Object biasedLock = new Object();
        log.info(ClassLayout.parseInstance(biasedLock).toPrintable());
    }
}
