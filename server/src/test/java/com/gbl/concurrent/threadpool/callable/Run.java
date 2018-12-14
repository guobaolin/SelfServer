package com.gbl.concurrent.threadpool.callable;

import com.gbl.concurrent.threadpool.forkjoin.MyRecursiveAction;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * Created by guobaolin on 2018/11/17.
 */
public class Run {

    @Test
    public void testCallable(){
        Callable<String>  callable = new Callable<String>() {

            private int age;

            {
                this.age = 100;
            }

            @Override
            public String call() throws Exception {
                Thread.sleep(8000);
                return "返回值 年龄是：" + age;
            }
        };

        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 5,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(1), new DefaultThreadFactory("1"),
                new ThreadPoolExecutor.DiscardPolicy());

        Future<String> future = executor.submit(callable);

        try {
            String s = future.get();
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<>(task);
        try {
            executorService.execute(futureTask);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test1() throws ExecutionException, InterruptedException {
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<>(task);
        Thread thread = new Thread(futureTask);
        thread.start();
    }

    @Test
    public void test11() throws InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(new MyRecursiveAction(1, 7));
        Thread.sleep(500000);
    }
}
