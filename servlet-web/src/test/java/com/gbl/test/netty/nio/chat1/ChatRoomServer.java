package com.gbl.test.netty.nio.chat1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by guobaolin on 2018/12/14.
 */
public class ChatRoomServer {

    private static final int port = 9999;
    private Charset charset = Charset.forName("UTF-8");
    private Selector selector;
    // 用来记录在线人数，以及昵称
    private static Set<String> users = new HashSet<>();

    private static String USER_EXIST = "system message: user exist, please change a name";
    // 相当于自定义协议格式，与客户端协商好
    private static String USER_CONTENT_SPILIT = "#@#";

    private static boolean flag = false;

    public void init() throws IOException {
        selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(port));
        // 非阻塞的方式
        serverSocketChannel.configureBlocking(false);
        // 注册到选择器上，设置为监听状态
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("Server is listening now...");

        while (true) {
            int readyChannels = selector.select();
            if (readyChannels == 0) continue;
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                dealWithSelectionKey(serverSocketChannel, key);
            }
        }
    }

    public void dealWithSelectionKey(ServerSocketChannel server, SelectionKey selectionKey) throws IOException {
        if (selectionKey.isAcceptable()) {
            SocketChannel socketChannel = server.accept();
            // 非阻塞模式
            socketChannel.configureBlocking(false);
            // 注册选择器，并设置为读取模式，收到一个连接请求，然后起一个SocketChannel，并注册到selector上，之后这个连接的数据，就由这个SocketChannel处理
            socketChannel.register(selector, SelectionKey.OP_READ);

            // 将此对应的channel设置为准备接受其他客户端请求
            selectionKey.interestOps(SelectionKey.OP_ACCEPT);
            System.out.println("Server is listening from client: " + socketChannel.getRemoteAddress());
            socketChannel.write(charset.encode("Please input your name."));
        }
        if (selectionKey.isReadable()) {
            // 返回该SelectionKey对应的 Channel，其中有数据需要读取
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            StringBuilder content = new StringBuilder();
            try {
                while (socketChannel.read(buffer) > 0) {
                    buffer.flip();
                    content.append(charset.decode(buffer));
                }
                System.out.println("Server is listening from client " + socketChannel.getRemoteAddress() + "data rev is: " + content);
                selectionKey.interestOps(SelectionKey.OP_READ);
            } catch (Exception e) {
                selectionKey.cancel();
                if (selectionKey.channel() != null) {
                    selectionKey.channel().close();
                }
            }
            if (content.length() > 0) {
                String[] arrayContent = content.toString().split(USER_CONTENT_SPILIT);
                // 注册用户
                if (arrayContent != null && arrayContent.length == 1) {
                    String name = arrayContent[0];
                    if (users.contains(name)) {
                        socketChannel.write(charset.encode(USER_EXIST));
                    } else {
                        users.add(name);
                        int num = OnlineNum(selector);
                        String message = "welcome " + name + " to chat room! Online numbers:" + num;
                        BroadCast(selector, null, message);
                    }
                } else if (arrayContent != null && arrayContent.length > 1) {
                    String name = arrayContent[0];
                    String message = content.substring(name.length() + USER_CONTENT_SPILIT.length());
                    message = name + " say " + message;
                    if (users.contains(name)) {
                        BroadCast(selector, socketChannel, message);
                    }
                }
            }
        }
    }

    public static int OnlineNum(Selector selector) {
        int res = 0;
        for (SelectionKey key : selector.keys()) {
            SelectableChannel targetChannel = key.channel();
            if (targetChannel instanceof SocketChannel) {
                res++;
            }
        }
        return res;
    }

    public void BroadCast(Selector selector, SocketChannel except, String content) throws IOException {
        // 广播数据到所有的SocketChannel中
        for (SelectionKey key : selector.keys()) {
            SelectableChannel targetChannel = key.channel();
            // 如果except不为空，不回发给发送此内容的客户端
            if (targetChannel instanceof SocketChannel && targetChannel != except) {
                SocketChannel dest = (SocketChannel) targetChannel;
                dest.write(charset.encode(content));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new ChatRoomServer().init();
    }

}