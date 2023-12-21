package com.moralok.concurrency.ch2;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.io.IOException;

@Slf4j
public class ThinLockingBaseTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        log.info("轻量级锁基础测试：无锁状态 -> 轻量级锁");

        Object lock = new Object();
        log.info("在偏向锁激活之前创建的对象为 =====> 无锁状态（可偏向额）");
        log.info(ClassLayout.parseInstance(lock).toPrintable());

        synchronized (lock) {
            log.info("即使是单线程无竞争获取锁，=====> 轻量级锁");
            log.info(ClassLayout.parseInstance(lock).toPrintable());
        }

        log.info("离开同步块后轻量级锁释放 =====> 无锁状态（可偏向的）");
        log.info(ClassLayout.parseInstance(lock).toPrintable());
    }
}
