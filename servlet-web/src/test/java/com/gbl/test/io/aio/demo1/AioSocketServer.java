package com.gbl.test.io.aio.demo1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by guobaolin on 2018/12/16.
 */
public class AioSocketServer {

    private static final Object waitObject = new Object();

    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        AsynchronousChannelGroup group = AsynchronousChannelGroup.withThreadPool(threadPool);
        final AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open(group);

        serverSocketChannel.bind(new InetSocketAddress("0.0.0.0", 8083));
        serverSocketChannel.accept(null, new ServerSocketChannelHandle(serverSocketChannel));

        synchronized (waitObject) {
            waitObject.wait();
        }
    }

}
