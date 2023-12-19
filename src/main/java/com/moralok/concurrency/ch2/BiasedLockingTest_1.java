package com.moralok.concurrency.ch2;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.io.IOException;

@Slf4j
public class BiasedLockingTest_1 {

    public static void main(String[] args) throws IOException {
        Object lock = new Object();
        // 无锁状态
        log.info(ClassLayout.parseInstance(lock).toPrintable());
        synchronized (lock) {
            // 为什么直接升级成轻量级锁了？？？
            log.info(ClassLayout.parseInstance(lock).toPrintable());
        }
        System.in.read();
    }
}
