package com.gbl.test.netty.aio.demo1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by guobaolin on 2018/12/16.
 */
public class AioSocketClient {

    public static void main(String[] args) throws IOException {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        AsynchronousChannelGroup group = AsynchronousChannelGroup.withThreadPool(threadPool);
        final AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open(group);
        socketChannel.connect(new InetSocketAddress("10.8.9.85", 8083), group, new ClientSocketChannelHandle(socketChannel));

    }

}
