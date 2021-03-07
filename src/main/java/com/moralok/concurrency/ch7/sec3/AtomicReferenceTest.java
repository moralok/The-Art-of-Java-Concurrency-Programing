package com.moralok.concurrency.ch7.sec3;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author moralok
 * @since 2021/3/7
 */
public class AtomicReferenceTest {

    private static AtomicReference<User> ar = new AtomicReference<>();

    public static void main(String[] args) {
        User tom = new User("tom", 15);
        ar.set(tom);
        User tim = new User("tim", 20);
        ar.compareAndSet(tom, tim);
        System.out.println(ar.get().getName());
        System.out.println(ar.get().getOld());
    }

    private static class User {
        private String name;
        private int old;
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
