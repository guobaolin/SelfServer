package com.gbl.test.netty.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by guobaolin on 2018/12/11.
 */
public class SelectorClient {

    static class Client extends Thread {
        private String name;
        private Random random = new Random(47);

        public Client(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                SocketChannel channel = SocketChannel.open();
                channel.configureBlocking(false);
                channel.connect(new InetSocketAddress(1234));
                while (!channel.finishConnect()) {
                    TimeUnit.MILLISECONDS.sleep(100);
                }
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                for (int i = 0; i < 5; i++) {
                    int rand = random.nextInt(10);
                    System.out.println("random = " + rand);
                    TimeUnit.SECONDS.sleep(5 * rand);
                    String str = "Message from " + name + ", number:" + i;
                    buffer.put(str.getBytes());
                    buffer.flip();
                    while (buffer.hasRemaining()) {
                        channel.write(buffer);
                    }
                    buffer.clear();
                }
                channel.close();
            } catch (Exception e) {

            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(new Client("Client--1"));
//        executorService.submit(new Client("Client--2"));
//        executorService.submit(new Client("Client--3"));
        executorService.shutdown();
        try {
            Thread.sleep(100000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
