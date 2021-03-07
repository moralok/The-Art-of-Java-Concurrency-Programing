package com.moralok.concurrency.ch7.sec4;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author moralok
 * @since 2021/3/7
 */
public class AtomicIntegerFieldUpdaterTest {

    private static AtomicIntegerFieldUpdater<User> ai = AtomicIntegerFieldUpdater.newUpdater(User.class, "old");

    public static void main(String[] args) {
        User tom = new User("tom", 15);
        System.out.println(ai.getAndIncrement(tom));
        System.out.println(ai.get(tom));
    }

    private static class User {
        private String name;
        public volatile int old;
        public User(String name, int old) {
            this.name = name;
            this.old = old;
        }

        public String getName() {
            return name;
        }

        public int getOld() {
            return old;
        }
    }
}
