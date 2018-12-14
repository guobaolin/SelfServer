package com.gbl.concurrent.threadpool.callable;

import java.util.concurrent.Callable;

/**
 * Created by guobaolin on 2018/11/17.
 */
public class Task implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("子线程在进行计算");
        Thread.sleep(3000);
        Integer.parseInt("q");
        return 123;
    }
}
