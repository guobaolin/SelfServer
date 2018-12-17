package com.gbl.test.io.nio.demo1;

import com.gbl.entity.User;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by guobaolin on 2018/12/17.
 */
public class HelloSelectorClient {
    private static Selector selector;
    private static final int port = 8082;

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        selector = Selector.open();
        if (socketChannel.connect(new InetSocketAddress(port))){
            User user = new User();
            user.setId(2);
            user.setAge("20");
            user.setName("guobaolin");
            user.setAddress("nanshan");
            byte[] bytes = ObjectAndByte.toByteArray(user);
            socketChannel.write(ByteBuffer.wrap(bytes));
            socketChannel.register(selector, SelectionKey.OP_READ);
        }else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
        while (true) {
            int n = selector.select();
            if (n == 0) continue;
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isConnectable()) {
                    doConnect(key);
                }
                if (key.isWritable()) {
                    doWrite(key);
                }
                if (key.isReadable()) {
                    doRead(key);
                }
            }
        }
    }

    public static void doConnect(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        if (channel.isConnectionPending()) {
            if (channel.finishConnect()) {
                channel.configureBlocking(false);
                channel.register(selector, SelectionKey.OP_WRITE);
            }
        }
    }

    public static void doWrite(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        if (StringUtils.isEmpty(key.attachment())){
            User user = new User();
            user.setId(2);
            user.setAge("20");
            user.setName("guobaolin");
            user.setAddress("nanshan");
            key.attach(user);
        }
        Object attachment = key.attachment();
        key.attach("");
        byte[] bytes = ObjectAndByte.toByteArray(attachment);
        channel.write(ByteBuffer.wrap(bytes));
        key.interestOps(SelectionKey.OP_READ);
    }

    public static void doRead(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);
        buffer.flip();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        User user = (User) ObjectAndByte.toObject(bytes);
        System.out.println(user);
        user.setId(user.getId() + 1);
        key.attach(user);
        key.interestOps(SelectionKey.OP_WRITE);
    }
}
