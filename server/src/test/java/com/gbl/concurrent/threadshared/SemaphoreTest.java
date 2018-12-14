package com.gbl.concurrent.threadshared;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * Created by guobaolin on 2018/11/6.
 */
public class SemaphoreTest {

    @Test
    public void test1() {
        Semaphore semaphore = new Semaphore(2);

        int available = semaphore.availablePermits();
        System.out.println("available=" + available);

        int drain = semaphore.drainPermits();
        System.out.println("drain=" + drain);

        int available1 = semaphore.availablePermits();
        System.out.println("available=" + available1);

        new Thread(() -> {
            semaphore.acquireUninterruptibly();
            System.out.println(Thread.currentThread().getName() + "begin timer=" + System.currentTimeMillis());
        }).start();
    }

    @Test
    public void test2() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(3);  //最多有3个线程同时运行

        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                try {
                    semaphore.acquire(); //获取许可
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "进入，当前已有" + (3 - semaphore.availablePermits()) + "线程并发");
                    Thread.sleep((long) Math.random() * 500); //随机sleep 0 ~ 500ms
                    int a = 0;
                    a = 1 / a;
                    semaphore.release();
                    System.out.println("线程" + Thread.currentThread().getName()
                            + "已离开，当前还有" + (3 - semaphore.availablePermits()) + "线程并发");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        Thread.sleep(100);

        System.out.println("等待许可的线程数 QueueLength=" + semaphore.getQueueLength());

        System.out.println("有没有线程在等待这个许可 hasQueuedThreads=" + semaphore.hasQueuedThreads());

    }

    @Test
    public void test3() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        System.out.println(Thread.currentThread().getName() + " start...");
        executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName() + " running...");
            System.out.println(Thread.currentThread().getName() + " shutdown...");
        });
        System.out.println(Thread.currentThread().getName() + " end...");

    }

    @Test
    public void test4(){
        Exchanger<String> exchanger = new Exchanger<>();
        ThreadA a = new ThreadA(exchanger);
        ThreadB b = new ThreadB(exchanger);
        a.start();
        b.start();
    }

    public void test5(){
        Phaser phaser = new Phaser(3){
            @Override
            protected boolean onAdvance(int phaser, int registeredParties){
                return false;
            }
        };
    }
}
