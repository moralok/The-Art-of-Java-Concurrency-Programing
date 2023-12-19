package com.moralok.concurrency.ch2;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class BiasedLockingTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        Object lock = new Object();
        // biasable，可偏向的
        log.info(ClassLayout.parseInstance(lock).toPrintable());
        synchronized (lock) {
            // biased，偏向锁状态
            log.info(ClassLayout.parseInstance(lock).toPrintable());
        }
        // 暂停可以使用 jstack 查看 main 线程 tid 和打印的结果进行对比
        System.in.read();
    }
}
