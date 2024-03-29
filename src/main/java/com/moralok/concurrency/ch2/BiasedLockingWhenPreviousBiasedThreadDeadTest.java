package com.moralok.concurrency.ch2;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class BiasedLockingWhenPreviousBiasedThreadDeadTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        log.info("测试：之前获得偏向锁的线程已死时，新线程获得的仍然是偏向锁");

        log.info("sleep 4000ms，等待偏向锁激活");
        TimeUnit.MILLISECONDS.sleep(4000);

        Object lock = new Object();
        log.info("Mark Word 初始为 =====> 匿名偏向锁");
        log.info(ClassLayout.parseInstance(lock).toPrintable());

        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                log.info("第一个线程 {} 获取锁 =====> 偏向锁", Thread.currentThread().getName());
                log.info(ClassLayout.parseInstance(lock).toPrintable());
            }
        }, "thread1");
        thread1.start();
        thread1.join();

        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                log.info("第二个线程 {} 获取锁，=====> 偏向锁", Thread.currentThread().getName());
                log.info("震惊！！！为什么两个 tid 相同啊，有什么复用机制吗");
                log.info(ClassLayout.parseInstance(lock).toPrintable());
            }
        }, "thread2");
        thread2.start();
        thread2.join();

        log.info("偏向锁等到竞争出现才释放锁，因此离开同步方法块后，Mark Word 不变");
        log.info(ClassLayout.parseInstance(lock).toPrintable());
    }
}
