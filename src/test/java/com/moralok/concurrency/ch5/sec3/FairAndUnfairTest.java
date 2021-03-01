package com.moralok.concurrency.ch5.sec3;

import org.junit.jupiter.api.Test;

/**
 * @author moralok
 * @since 2021/3/1 6:44 下午
 */
class FairAndUnfairTest {

    @Test
    void testLock1() {
        FairAndUnfair.testLock(FairAndUnfair.fairLock);
    }

    @Test
    void testLock2() {
        FairAndUnfair.testLock(FairAndUnfair.unfairLock);
    }
}
