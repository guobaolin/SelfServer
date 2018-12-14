package com.gbl.pool;

import java.util.NoSuchElementException;

/**
 * Created by guobaolin on 2018/10/25.
 */
public interface ObjectPool<T> extends Cloneable {
    T borrowObject() throws Exception, NoSuchElementException, IllegalStateException;

    void returnObject(T var1) throws Exception;

    void invalidateObject(T varl) throws Exception;

    void addObject() throws Exception, IllegalStateException,UnsupportedOperationException;

    int getNumIdle();

    int getNumActive();

    void clear() throws Exception, UnsupportedOperationException;

    void close();
}
