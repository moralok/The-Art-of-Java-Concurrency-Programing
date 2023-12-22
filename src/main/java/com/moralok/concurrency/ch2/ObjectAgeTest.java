package com.moralok.concurrency.ch2;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ObjectAgeTest {

    public static void main(String[] args) throws InterruptedException {
        log.info("测试 Mark Word 中的分代年龄");

        Object lock = new Object();
        log.info("Mark Word 初始为 =====> 无锁状态，age: 0");
        log.info(ClassLayout.parseInstance(lock).toPrintable());
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        log.info("GC 后 =====> 无锁状态，age: 1");
        log.info(ClassLayout.parseInstance(lock).toPrintable());
    }
}
