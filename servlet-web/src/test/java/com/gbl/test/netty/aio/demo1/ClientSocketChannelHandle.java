package com.gbl.test.netty.aio.demo1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * Created by guobaolin on 2018/12/16.
 */
public class ClientSocketChannelHandle implements CompletionHandler<Void, Object> {

    private AsynchronousSocketChannel socketChannel;

    public ClientSocketChannelHandle(AsynchronousSocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void completed(Void result, Object attachment) {
        try {
            AsynchronousChannelGroup group = (AsynchronousChannelGroup) attachment;
            final AsynchronousSocketChannel client = AsynchronousSocketChannel.open(group);
            client.connect(new InetSocketAddress("10.8.9.85", 8083), null, new CompletionHandler<Void, Object>() {
                @Override
                public void completed(Void result, Object attachment) {
                    String msg = " client asks time, times: " + System.currentTimeMillis();
                    client.write(ByteBuffer.wrap(msg.getBytes()));
                    System.out.println(Thread.currentThread().getName() + "client send data:" + msg);

                    final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    client.read(byteBuffer, this, new CompletionHandler<Integer, CompletionHandler<Void, Object>>() {
                        @Override
                        public void completed(Integer result, CompletionHandler<Void, Object> attachment) {
                            System.out.println(Thread.currentThread().getName() + " client get response: " + new String(byteBuffer.array()) + ". " + System.currentTimeMillis());
                            try {
                                byteBuffer.clear();
                                if (client != null) client.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void failed(Throwable exc, CompletionHandler<Void, Object> attachment) {
                            System.out.println(Thread.currentThread().getName() + " read faield");
                        }
                    });
                }

                @Override
                public void failed(Throwable exc, Object attachment) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void failed(Throwable exc, Object attachment) {
        System.out.println(Thread.currentThread().getName() + " client send field...");
    }
}
