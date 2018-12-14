package com.gbl.concurrent.threadshared;

import java.util.concurrent.Exchanger;

/**
 * Created by guobaolin on 2018/11/6.
 */
public class ThreadB extends Thread {

    private Exchanger<String> exchanger;

    public ThreadB(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run(){
        try {
            System.out.println("在线程B中的到线程B的值=" + exchanger.exchange("中国人B"));
            System.out.println("B end! ");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
