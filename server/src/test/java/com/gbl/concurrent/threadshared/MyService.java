package com.gbl.concurrent.threadshared;

import java.util.concurrent.Phaser;

/**
 * Created by guobaolin on 2018/11/7.
 */
public class MyService {

    public Phaser phaser;

    public MyService(Phaser phaser) {
        this.phaser = phaser;
    }

    public void testMethodA() {
        try {
            System.out.println(Thread.currentThread().getName() + " begin A1 " + System.currentTimeMillis());
//            Thread.sleep(3000);
            System.out.println(phaser.getArrivedParties());
            phaser.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread().getName() + " end A1 " + System.currentTimeMillis());

            System.out.println(Thread.currentThread().getName() + " begin A2 " + System.currentTimeMillis());
//            Thread.sleep(3000);
            System.out.println(phaser.getArrivedParties());
            phaser.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread().getName() + " end A2 " + System.currentTimeMillis());

            System.out.println(Thread.currentThread().getName() + " begin A3 " + System.currentTimeMillis());
//            Thread.sleep(3000);
            System.out.println(phaser.getArrivedParties());
            phaser.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread().getName() + " end A3 " + System.currentTimeMillis());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testMethodB() {
        try {
            System.out.println(Thread.currentThread().getName() + " begin B1 " + System.currentTimeMillis());
            System.out.println(phaser.getArrivedParties());
            phaser.arrive();
            System.out.println(Thread.currentThread().getName() + " end B1 " + System.currentTimeMillis());

            System.out.println(Thread.currentThread().getName() + " begin B2 " + System.currentTimeMillis());
            System.out.println(phaser.getArrivedParties());
            phaser.arrive();
            System.out.println(Thread.currentThread().getName() + " end B2 " + System.currentTimeMillis());

            System.out.println(Thread.currentThread().getName() + " begin B3 " + System.currentTimeMillis());
            System.out.println(phaser.getArrivedParties());
            phaser.arrive();
            System.out.println(Thread.currentThread().getName() + " end B3 " + System.currentTimeMillis());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Phaser phaser = new Phaser(3);
        MyService service = new MyService(phaser);

        Thread a = new Thread() {
            @Override
            public void run() {
                service.testMethodA();
            }
        };
        a.setName("A");
        a.start();

        Thread b = new Thread() {
            @Override
            public void run() {
                service.testMethodA();
            }
        };
        b.setName("B");
        b.start();

        Thread c = new Thread() {
            @Override
            public void run() {
                service.testMethodB();
            }
        };
        c.setName("C");
        c.start();
    }
}
