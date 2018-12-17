package com.gbl.test.netty.nio.demo1;

import com.gbl.entity.User;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.OffsetDateTime;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by guobaolin on 2018/12/17.
 */
public class HelloSelectorServer {

    private static Selector selector;
    private static final int port = 8083;
    private static ByteBuffer buffer = ByteBuffer.allocate(1024);

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            int n = selector.select();
            if (n == 0) continue;
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    doAccept(key);
                }
                if (key.isReadable()) {
                    doRead(key);
                }
                if (key.isWritable()) {
                    doWrite(key);
                }
            }
        }
    }

    public static void doAccept(SelectionKey key) throws IOException {
        ServerSocketChannel serverTemp = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = serverTemp.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    public static void doRead(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        buffer.clear();
        while (socketChannel.read(buffer) > 0) {
            buffer.flip();
            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
            Object object = ObjectAndByte.toObject(bytes);
            if (object !=null) {
                User user = (User) object;
                user.setName("Hello "+ user.getName());
                key.attach(user);
            }
        }
        key.interestOps(SelectionKey.OP_WRITE);
    }

    public static void doWrite(SelectionKey key){
        SocketChannel socketChannel = (SocketChannel) key.channel();
        buffer.clear();
        Object attachment = key.attachment();
        key.attach("");
        byte[] bytes = ObjectAndByte.toByteArray(attachment);
        try {
            socketChannel.write(ByteBuffer.wrap(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
        key.interestOps(SelectionKey.OP_READ);
    }
}
