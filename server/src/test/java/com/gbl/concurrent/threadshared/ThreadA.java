package com.gbl.concurrent.threadshared;

import java.util.concurrent.Exchanger;

/**
 * Created by guobaolin on 2018/11/6.
 */
public class
ThreadA extends Thread {

    private Exchanger<String> exchanger;

    public ThreadA(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run(){
        try {
            System.out.println("在线程A中的到线程B的值=" + exchanger.exchange("中国人A"));
            System.out.println("A end! ");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
