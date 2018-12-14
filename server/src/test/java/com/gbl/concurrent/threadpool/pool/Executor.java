package com.gbl.concurrent.threadpool.pool;

/**
 * Created by guobaolin on 2018/11/7.
 */
public interface Executor {

    void execute(Runnable command);

}
