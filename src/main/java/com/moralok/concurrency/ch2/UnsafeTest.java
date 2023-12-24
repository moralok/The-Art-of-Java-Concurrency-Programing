package com.moralok.concurrency.ch2;

import org.openjdk.jol.info.ClassLayout;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeTest {

    private static final Unsafe unsafe = getUnsafe();

    private static final long aIntOffset;
    private static final long aByteOffset;

    static {
        try {
            aIntOffset = unsafe.objectFieldOffset(UnsafeTest.class.getDeclaredField("aInt"));
            aByteOffset = unsafe.objectFieldOffset(UnsafeTest.class.getDeclaredField("aByte"));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    private volatile int aInt;
    private volatile byte aByte;

    public int getInt() {
        return aInt;
    }
    public void incr(int delta) {
        unsafe.getAndAddInt(this, aIntOffset, delta);
    }

    public static void main(String[] args) {
        UnsafeTest unsafeTest = new UnsafeTest();
        unsafeTest.incr(1);
        System.out.println(unsafeTest.getInt());
        unsafeTest.incr(2);
        System.out.println(unsafeTest.getInt());
        // 打印字段在对象内部结构中的偏移量
        System.out.println(unsafeTest.aIntOffset);
        System.out.println(unsafeTest.aByteOffset);
        System.out.println(ClassLayout.parseInstance(unsafeTest).toPrintable());
    }

    // Unsafe.getUnsafe() 需要系统类加载器加载的类才可以调用
    private static Unsafe getUnsafe() {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            Unsafe unsafe = (Unsafe) f.get(null);
            return unsafe;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
