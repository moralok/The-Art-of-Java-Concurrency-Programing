package com.moralok.concurrency.ch2;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.io.IOException;

@Data
@Slf4j
public class ObjectInternalTest {

    private byte aByte;

    private int aInt;

    public static void main(String[] args) throws IOException {
        ObjectInternalTest objectInternalTest = new ObjectInternalTest();
        log.info(ClassLayout.parseInstance(objectInternalTest).toPrintable());
        System.in.read();
    }
}
