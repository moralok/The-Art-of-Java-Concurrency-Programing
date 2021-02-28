package com.moralok.concurrency.ch4;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @author moralok
 * @since 2021/2/27
 */
public class MultiThread {

    /**
     * 查看一个普通的Java程序包含哪些线程
     *
     * [7] JDWP Command Reader 【Java Platform Debugger Architecture】【Java Debug Wire Protocol 】
     * [6] JDWP Event Helper Thread
     * [5] JDWP Transport Listener: dt_socket
     * [4] Signal Dispatcher 分发处理发送给JVM信息的线程
     * [3] Finalizer 调用对象finalize方法的线程
     * [2] Reference Handler 清楚Reference的线程
     * [1] main main线程，用户程序入口
     */
    public static void main(String[] args) {
        // 获取Java线程管理MXBean
        ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();
        // 不需要获取同步的monitor和synchronizer信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = mxBean.dumpAllThreads(false, false);
        // 遍历线程信息，仅打印线程ID和线程名称信息
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
        }
    }
}
