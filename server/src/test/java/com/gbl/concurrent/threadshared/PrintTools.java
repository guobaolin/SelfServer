package com.gbl.concurrent.threadshared;

import java.util.concurrent.Phaser;

/**
 * Created by guobaolin on 2018/11/7.
 */
public class PrintTools {

    public static Phaser phaser;

    public static void methodA(){
        System.out.println(Thread.currentThread().getName()+"A1 begin="+System.currentTimeMillis());
        phaser.arriveAndAwaitAdvance();
        System.out.println(Thread.currentThread().getName()+"A1 end="+System.currentTimeMillis());

        System.out.println(Thread.currentThread().getName()+"A2 begin="+System.currentTimeMillis());
        phaser.arriveAndAwaitAdvance();
        System.out.println(Thread.currentThread().getName()+"A2 end="+System.currentTimeMillis());
    }

    public static void methodB() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"A1 begin="+System.currentTimeMillis());
        Thread.sleep(5000);
        phaser.arriveAndAwaitAdvance();
        System.out.println(Thread.currentThread().getName()+"A1 end="+System.currentTimeMillis());

        System.out.println(Thread.currentThread().getName()+"A2 begin="+System.currentTimeMillis());
        Thread.sleep(5000);
        phaser.arriveAndAwaitAdvance();
        System.out.println(Thread.currentThread().getName()+"A2 end="+System.currentTimeMillis());
    }
}
