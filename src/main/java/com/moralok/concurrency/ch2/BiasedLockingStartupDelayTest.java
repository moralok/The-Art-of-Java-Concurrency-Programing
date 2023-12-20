package com.moralok.concurrency.ch2;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class BiasedLockingStartupDelayTest {
    // 测试延迟偏向
    public static void main(String[] args) throws IOException, InterruptedException {
        log.info("测试：偏向锁是延迟激活的");

        Object lock = new Object();
        log.info("Mark Word 初始为 =====> 无锁状态（非可偏向的）");
        log.info(ClassLayout.parseInstance(lock).toPrintable());

        // 默认情况下偏向延迟的设置为 -XX:BiasedLockingStartupDelay=4000
        log.info("sleep 4000ms，等待偏向锁激活");
        TimeUnit.MILLISECONDS.sleep(4000);

        log.info("偏向锁激活之后，新创建的对象的对象头的 Mark Word 是 =====> 匿名偏向锁");
        Object biasedLock = new Object();
        log.info(ClassLayout.parseInstance(biasedLock).toPrintable());

        log.info("偏向锁激活之前创建的对象的对象头的 Mark Word 仍然是 =====> 无锁状态");
        log.info(ClassLayout.parseInstance(lock).toPrintable());
    }
}
