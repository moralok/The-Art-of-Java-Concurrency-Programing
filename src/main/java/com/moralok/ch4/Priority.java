package com.moralok.ch4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author moralok
 * @since 2021/2/27
 */
public class Priority {

    private static volatile boolean notStart = true;
    private static volatile boolean notEnd = true;

    /**
     * 线程顺序不能依赖于优先级
     * 从结果看，两种优先级的统计结果相近
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        int size = 10;
        List<Job> jobList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            int priority = i < 5 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
            Job job = new Job(priority);
            jobList.add(job);
            Thread thread = new Thread(job, "Thread-" + i);
            thread.setPriority(priority);
            thread.start();
        }
        notStart = false;
        TimeUnit.SECONDS.sleep(10);
        notEnd = false;
        for (Job job : jobList) {
            System.out.println("Job priority : " + job.priority + ", Count : " + job.jobCount);
        }
    }

    static class Job implements Runnable {

        private int priority;
        private long jobCount;

        public Job(int priority) {
            this.priority = priority;
        }

        @Override
        public void run() {
            while (notStart) {
                Thread.yield();
            }
            while (notEnd) {
                Thread.yield();
                jobCount++;
            }
        }
    }
}
