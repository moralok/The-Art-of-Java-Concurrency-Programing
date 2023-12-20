package com.moralok.concurrency.ch2;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.io.IOException;

@Slf4j
public class BiasedLockingStartupNoDelayTest {

    // 虚拟机参数设置为 -XX:BiasedLockingStartupDelay=0
    public static void main(String[] args) throws IOException, InterruptedException {
        log.info("测试：关闭偏向锁的延迟偏向");

        Object lock = new Object();
        log.info("在虚拟机一启动，新创建的对象的对象头的 Mark Word 就是 =====> 匿名偏向锁");
        log.info(ClassLayout.parseInstance(lock).toPrintable());
    }
}
