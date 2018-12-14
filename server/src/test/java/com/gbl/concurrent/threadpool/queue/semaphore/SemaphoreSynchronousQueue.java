package com.gbl.concurrent.threadpool.queue.semaphore;

import java.util.concurrent.Semaphore;

/**
 * Created by guobaolin on 2018/11/9.
 */
public class SemaphoreSynchronousQueue<E> {
    E item = null;
    Semaphore sync = new Semaphore(0);

    Semaphore send = new Semaphore(1);

    Semaphore recv = new Semaphore(0);

    public E take() throws InterruptedException {
        recv.acquire();
        E x = item;
        sync.release();
        send.release();
        Thread.sleep(500);
        return x;
    }

    public void put (E x) throws InterruptedException{
        send.acquire();
        item = x;
        recv.release();
        sync.acquire();
    }
}
