package com.gbl.pool;

import java.io.PrintWriter;
import java.util.Deque;

/**
 * Created by guobaolin on 2018/10/25.
 */
public interface PooledObject<T> extends Comparable<PooledObject<T>> {
    T getObject();  // 获取对象

    long getCreateTime();   // 获取创建时间

    long getActiveTimeMillis();

    long getIdleTimeMillis();   //获取空闲时间

    long getLastBorrowTime(); // 最后一次借用时间

    long getLastReturnTime();

    long getLastUsedTime();

    int compareTo(PooledObject<T> var1);

    boolean equals(Object var1);

    int hashCode();

    String toString();

    boolean startEvictionTest();

    boolean endEvictionTest(Deque<PooledObject<T>> var1);

    boolean allocate();

    boolean deallocate();

    void invalidate();

    void setLogAbandoned(boolean var1);

    void use();

    void printStackTrace(PrintWriter var1);

    PooledObjectState getState();

    void markAbandoned();

    void markReturning();
}
