package com.gbl.concurrent.threadpool.queue.generic;

/**
 * Created by guobaolin on 2018/11/9.
 */
public class NativeSynchronousQueue<E> {
    boolean putting = false;
    E item = null;

    public synchronized E take() throws InterruptedException {
        while (item == null) {
            this.wait();
        }
        E e = item;
        item = null;
        this.notifyAll();
        return e;
    }

    public synchronized void put(E e) throws InterruptedException {
        if (e == null)
            return;
        while (putting){
            this.wait();
        }
        putting = true;
        item = e;
        this.notifyAll();
        while (item != null){
            this.wait();
        }
        putting = false;
        this.notifyAll();
    }
}
