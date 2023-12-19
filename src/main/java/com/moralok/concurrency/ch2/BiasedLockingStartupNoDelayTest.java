package com.moralok.concurrency.ch2;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.io.IOException;

@Slf4j
public class BiasedLockingStartupNoDelayTest {

    // 虚拟机参数设置为 -XX:BiasedLockingStartupDelay=0
    public static void main(String[] args) throws IOException, InterruptedException {
        Object lock = new Object();
        // 创建的对象的对象头默认是没有设置线程 ID 的偏向锁状态
        log.info(ClassLayout.parseInstance(lock).toPrintable());
    }
}
