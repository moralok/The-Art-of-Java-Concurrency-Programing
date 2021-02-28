package com.moralok.ch4.sec3;

/**
 * @author moralok
 * @since 2021/2/28
 */
public class Synchronized {

    /**
     * javap -v file
     *
     * @param args
     */
    public static void main(String[] args) {
        // 对Synchronized Class对象进行加锁
        synchronized (Synchronized.class) {

        }
        m();
    }

    static synchronized void m() {

    }
}
