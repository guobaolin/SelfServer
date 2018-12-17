package com.gbl.test.netty.aio.demo3;

import com.gbl.entity.User;
import com.gbl.test.netty.nio.demo1.ObjectAndByte;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * Created by guobaolin on 2018/12/17.
 */
public class HelloAsynchronousServer {
    private static CountDownLatch latch = new CountDownLatch(1);
    private static AsynchronousServerSocketChannel serverSocketChannel;
    private static final int port = 8083;

    public static void main(String[] args) throws IOException {
        serverSocketChannel = AsynchronousServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(port));
        serverSocketChannel.accept(serverSocketChannel, new CompletionHandler<AsynchronousSocketChannel, AsynchronousServerSocketChannel>(){
            @Override
            public void completed(AsynchronousSocketChannel result, AsynchronousServerSocketChannel attachment) {
                attachment.accept(attachment, this);

                ByteBuffer buffer = ByteBuffer.allocate(1024);
                result.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                    @Override
                    public void completed(Integer result, ByteBuffer attachment) {
                        attachment.flip();
                        byte[] bytes = new byte[attachment.remaining()];
                        Object o = ObjectAndByte.toObject(bytes);
                        if (o != null) {
                            User user = (User) o;
                            System.out.println(user);
                            user.setName("Hello " + user.getName());

                        }
                    }

                    @Override
                    public void failed(Throwable exc, ByteBuffer attachment) {
                        exc.printStackTrace();
                    }
                });
            }

            @Override
            public void failed(Throwable exc, AsynchronousServerSocketChannel attachment) {
                exc.printStackTrace();
//                attachment.latch.countDown();
            }
        });
    }

}
