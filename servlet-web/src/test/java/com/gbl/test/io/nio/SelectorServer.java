package com.gbl.test.io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by guobaolin on 2018/12/11.
 */
public class SelectorServer {

    private static final int PORT = 1234;
    private static ByteBuffer buffer = ByteBuffer.allocate(1024);

    public static void main(String[] args) {
        try {
            ServerSocketChannel socketChannel = ServerSocketChannel.open();
            socketChannel.bind(new InetSocketAddress(PORT));
            socketChannel.configureBlocking(false);
            // 1.register()
            Selector selector = Selector.open();
            socketChannel.register(selector, 0);
//            System.out.println("register channel, channel number is:" + selector.keys().size());

            while (true) {
                // 2.selector()
                int n = selector.select();
                if (n == 0) {
                    continue;
                }
                // 3.轮询SelectionKey
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    // 如果满足Acceptable条件，则必定是一个ServerSocketChannel
                    if (key.isAcceptable()) {
                        ServerSocketChannel sscTemp = (ServerSocketChannel) key.channel();
                        // 得到一个连接好的SocketChannel，并把它注册到Selector上，兴趣操作为READ
                        SocketChannel channel = sscTemp.accept();
                        channel.configureBlocking(false);
                        channel.register(selector, SelectionKey.OP_READ);
                        iterator.remove();
//                        System.out.println("register channel, channel number is:" + selector.keys().size());
                    }
                    // 如果满足Readable条件，则必定是一个SocketChannel
                    if (key.isReadable()) {
                        // 读取通道中的数据
                        SocketChannel channel = (SocketChannel) key.channel();
                        readFromChannel(channel);
                        key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);
                    }

//                    if (key.isWritable()) {
//                        // 写数据到通道中
//                        SocketChannel channel = (SocketChannel) key.channel();
//                        writeFromChannel(channel);
//                    }
                    // 4.remove SelectionKey
//                    iterator.remove();
                }
            }
        } catch (Exception e) {

        }
    }

    private static void readFromChannel(SocketChannel channel) {
        buffer.clear();
        try {
            while (channel.read(buffer) > 0) {
                buffer.flip();
                byte[] bytes = new byte[buffer.remaining()];
                buffer.get(bytes);
                System.out.println("read from client:" + new String(bytes));
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private static void writeFromChannel(SocketChannel channel) {
        buffer.clear();
        try {
            while (channel.read(buffer) > 0) {
                buffer.flip();
                byte[] bytes = new byte[buffer.remaining()];
                buffer.get(bytes);
                System.out.println("read from client:" + new String(bytes));
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

}
