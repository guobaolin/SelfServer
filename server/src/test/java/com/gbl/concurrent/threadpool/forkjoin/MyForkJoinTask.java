package com.gbl.concurrent.threadpool.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by guobaolin on 2018/11/19.
 */
public class MyForkJoinTask extends RecursiveTask<List<String>> {
    private static final long serialVersionUID = 4960060859900689866L;

    private int THRESHOLD = 2;
    private int start;
    private int end;

    public MyForkJoinTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected List<String> compute() {
        List<String> list = new ArrayList<>();

        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            for (int i = start; i < end; i++) {
                try {
                    Thread.currentThread().sleep(10);
                } catch (Exception e) {
                }
                System.out.println("线程池中的线程数目：" + MyForkJoinTask.getPool().getPoolSize()
                        + ",队列中等待执行的任务数目：" + MyForkJoinTask.getPool().getQueuedTaskCount());
                list.add("wo");
            }
            return list;
        }
        int middle = (end + start) / 2;
        ForkJoinTask<List<String>> task1 = new MyForkJoinTask(start, middle);
        ForkJoinTask<List<String>> task2 = new MyForkJoinTask(middle, end);
        invokeAll(task1, task2);

        List<String> leftResult = task1.join();
        List<String> rightResult = task2.join();
        list.addAll(rightResult);
        list.addAll(leftResult);

        return list;
    }

    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 330; i++) {
            list.add("i---------" + i);
        }
        System.out.println(list.size());
        long s1 = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();

        Future<List<String>> ss = pool.submit(new MyForkJoinTask(0, list.size()));

        pool.awaitTermination(1, TimeUnit.SECONDS);

        pool.shutdown();

        long s2 = System.currentTimeMillis();

        long s = s2 - s1;

        System.out.println(s);
    }

}
