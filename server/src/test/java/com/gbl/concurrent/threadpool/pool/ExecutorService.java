package com.gbl.concurrent.threadpool.pool;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by guobaolin on 2018/11/7.
 */
public interface ExecutorService extends Executor {

    /**
     * 终止当前线程池，但是执行中的任务会执行到结束，等待的任务不被执行
     */
    void shutdown();

    /**
     * 终止当前线程池，执行中的任务立即结束，等待的任务不被执行
     *
     * @return
     */
    List<Runnable> shutdownNow();

    /**
     * 如果调用了上面的两个shutdown方法，就返回true
     *
     * @return
     */
    boolean isShutdown();

    /**
     * 如果在shutdown之后，所有任务也都结束了，线程池处于终结状态，那么返回true
     *
     * @return
     */
    boolean isTerminated();

    /**
     * 阻塞，直到所有任务在关闭请求后完成执行，或超时发生，或当前线程被中断，无论哪个线程最先发生。
     *
     * @param timeout
     * @param unit
     * @return
     * @throws InterruptedException
     */
    boolean awaitTermination(long timeout, TimeUnit unit)
            throws InterruptedException;

    /**
     * 在线程池中提交一个callable类型的任务
     *
     * @param task
     * @param <T>
     * @return
     */
    <T> Future<T> submit(Callable<T> task);

    /**
     * 在线程池中提交一个runable类型的任务
     *
     * @param task
     * @param result
     * @param <T>
     * @return
     */
    <T> Future<T> submit(Runnable task, T result);

    /**
     * 在线程池中提交一个runable类型的任务
     *
     * @param task
     * @return
     */
    Future<?> submit(Runnable task);

    /**
     * 提交多个任务，并返回与每个任务对应的Future
     *
     * @param tasks
     * @param <T>
     * @return
     * @throws InterruptedException
     */
    <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
            throws InterruptedException;

    /**
     * 提交多个任务，并在给定时间内返回与每个任务对应的Future
     *
     * @param tasks
     * @param timeout
     * @param unit
     * @param <T>
     * @return
     * @throws InterruptedException
     */
    <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks,
                                  long timeout, TimeUnit unit)
            throws InterruptedException;

    /**
     * 提交多个任务，一旦有一个任务完成，就会终止其他任务的执行，
     * 如果没有一个任务完成，那么就会抛出异常
     *
     * @param tasks
     * @param <T>
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    <T> T invokeAny(Collection<? extends Callable<T>> tasks)
            throws InterruptedException, ExecutionException;

    /**
     * 提交多个任务，在给定时间内一旦有一个任务完成，就会终止其他任务的执行，
     * 如果没有一个任务完成，那么就会抛出异常
     *
     * @param tasks
     * @param timeout
     * @param unit
     * @param <T>
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    <T> T invokeAny(Collection<? extends Callable<T>> tasks,
                    long timeout, TimeUnit unit)
            throws InterruptedException, ExecutionException, TimeoutException;
}
