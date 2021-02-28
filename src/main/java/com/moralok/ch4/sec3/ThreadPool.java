package com.moralok.ch4.sec3;

/**
 * @author moralok
 * @since 2021/2/28
 */
public interface ThreadPool<Job extends Runnable> {

    /**
     * 执行一个Job
     * @param job
     */
    void execute(Job job);

    /**
     * 关闭线程池
     */
    void shutdown();

    /**
     * 增加工作者线程
     * @param num
     */
    void addWorkers(int num);

    /**
     * 增加工作者线程
     * @param num
     */
    void removeWorker(int num);

    /**
     * 得到正在等待执行的任务数量
     * @return
     */
    int getJobSize();
}
