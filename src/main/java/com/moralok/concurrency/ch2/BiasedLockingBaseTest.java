package com.moralok.concurrency.ch2;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class BiasedLockingBaseTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        log.info("偏向锁基础测试：匿名偏向锁 -> 偏向锁");

        log.info("sleep 4000ms，等待偏向锁激活");
        TimeUnit.MILLISECONDS.sleep(4000);

        Object lock = new Object();
        log.info("Mark Word 初始为 =====> 匿名偏向锁");
        log.info(ClassLayout.parseInstance(lock).toPrintable());

        synchronized (lock) {
            log.info("{} 获取锁 =====> 偏向锁", Thread.currentThread().getName());
            log.info(ClassLayout.parseInstance(lock).toPrintable());

            log.info("暂停 10s，可以使用 jstack 查看线程 tid 和 Mark Word 进行对比");
            TimeUnit.SECONDS.sleep(10);
        }

        log.info("偏向锁等到竞争出现才释放锁，因此离开同步方法块后，Mark Word 仍然不变");
        log.info(ClassLayout.parseInstance(lock).toPrintable());
    }
}
