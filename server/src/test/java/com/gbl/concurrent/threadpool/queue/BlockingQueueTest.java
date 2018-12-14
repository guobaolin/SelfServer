package com.gbl.concurrent.threadpool.queue;

import com.gbl.concurrent.threadpool.queue.generic.NativeSynchronousQueue;
import com.gbl.concurrent.threadpool.queue.semaphore.SemaphoreSynchronousQueue;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * Created by guobaolin on 2018/11/8.
 */
public class BlockingQueueTest {

    @Test
    public void testLinkedBlockingQueue() throws InterruptedException {
//        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(1);

        SynchronousQueue<Runnable> queue = new SynchronousQueue<>();

//        LinkedBlockingQueue<String> queue1 = new LinkedBlockingQueue<>(3);
//        queue1.put("a");

//        System.out.println(queue.offer("1"));
//        System.out.println(queue.offer("2"));
//        System.out.println(queue.poll());

//        System.out.println(queue.drainTo(queue1, 1));
//        System.out.println(queue1.remainingCapacity());
//        queue1.stream().forEach(System.out::println);

        ThreadPoolExecutor service = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, queue);
//        service.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 0; i < 3; i++) {
            try {
                service.execute(() -> {
//                try {
//                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                });
            } catch (Exception ignore) {
                System.out.println(ignore.getMessage());
            }
        }
        Thread.sleep(1000L);

    }

    @Test
    public void testArrayBlockingQueue() {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(1);
        System.out.println(queue.offer("1"));
        System.out.println(queue.offer("2"));
        System.out.println(queue.poll());
        System.out.println(queue.offer("3"));
    }

    @Test
    public void testSynchronousQueue() throws InterruptedException {
        SynchronousQueue<Runnable> queue = new SynchronousQueue<>();

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    queue.put(() -> {
                        System.out.println(Thread.currentThread().getName() + " hello !!!");
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
            new Thread(() -> {
                try {
                    Runnable runnable = queue.take();
                    String single = runnable == null ? "" : "not";
                    System.out.println("runnable is " + single + " null");
                    System.out.println("take-> " + Thread.currentThread().getName());
                    new Thread(runnable).start();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        Thread.sleep(10000L);
    }

    @Test
    public void testSynchronousQueue2() throws InterruptedException {
        final NativeSynchronousQueue<Integer> queue = new NativeSynchronousQueue<Integer>();

        for (int i = 0; i < 2; i++) {
            final int a = i;
            new Thread(() -> {
//                System.out.println("put thread start");
                try {
                    queue.put(a);
                } catch (InterruptedException e) {
                }
//                System.out.println("put thread end");
            }).start();
        }

        for (int i = 0; i < 2; i++) {

            new Thread(() -> {
//                System.out.println("take thread start");
                try {
                    System.out.println("take from putThread: " + queue.take());
                } catch (InterruptedException e) {
                }
//                System.out.println("take thread end");
            }).start();
            Thread.sleep(100);
        }


    }

    @Test
    public void testSynchronousQueue3() throws InterruptedException {
        final SemaphoreSynchronousQueue<StringBuffer> queue = new SemaphoreSynchronousQueue<>();
        StringBuffer stringBuffer = new StringBuffer().append(0);

        for (int i = 0; i < 20; i++) {
            final int a = i + 1;
            new Thread(() -> {
                try {
                    queue.put(stringBuffer.deleteCharAt(0).append(a));
                } catch (InterruptedException e) {
                }
            }).start();
        }

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                try {
                    System.out.println("take from putThread: " + queue.take().charAt(0));
                } catch (InterruptedException e) {
                }
            }).start();
        }

        Thread.sleep(6000);

    }

    @Test
    public void testThreadPoolExecutor() {

        SynchronousQueue<Runnable> queue = new SynchronousQueue<>(true);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();

        ThreadPoolExecutor executorService = new ThreadPoolExecutor(1, 2, 1, TimeUnit.SECONDS,
                queue, threadFactory, handler);

        for (int i = 0; i < 10; i++) {
            final int j = i + 1;
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " j=" + j);
            });
        }
    }

    @Test
    public void testThreadPoolExecutor1() {
        int CAPACITY = (1 << 29) - 1;
        int RUNNING = -1 << 29;

        String s = "1110 0000 0000 0000 0000 0000 0000 0000";

//        System.out.println(Integer.toBinaryString(CAPACITY));
//        System.out.println(Integer.toBinaryString(RUNNING));
        System.out.println(RUNNING);
    }

}
