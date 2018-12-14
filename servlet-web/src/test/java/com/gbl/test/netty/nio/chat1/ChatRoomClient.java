package com.gbl.test.netty.nio.chat1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by guobaolin on 2018/12/14.
 */
public class ChatRoomClient {

    private Selector selector = null;
    static final int port = 9999;
    private Charset charset = Charset.forName("UTF-8");
    private SocketChannel socketChannel;
    private String name = "";
    private static String USER_EXIST = "system message: user exist, please change a name";
    private static String USER_CONTENT_SPILIT = "#@#";

    public void init() throws IOException {
        selector = Selector.open();
        socketChannel = SocketChannel.open();
        socketChannel.bind(new InetSocketAddress("127.0.0.1", port));
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        new Thread(new ClientThread()).start();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if ("".equals(line)) continue;
            if ("".equals(name)) {
                name = line;
                line = name + USER_CONTENT_SPILIT;
            }else {
                line = name + USER_CONTENT_SPILIT + line;
            }
            socketChannel.write(charset.encode(line));
        }
    }

    private class ClientThread implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    int readyChannels = selector.select();
                    if (readyChannels == 0) continue;
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        dealWithSelectionKey(key);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void dealWithSelectionKey(SelectionKey sk) throws IOException {
            if(sk.isReadable())
            {
                //使用 NIO 读取 Channel中的数据，这个和全局变量sc是一样的，因为只注册了一个SocketChannel
                //sc既能写也能读，这边是读
                SocketChannel sc = (SocketChannel)sk.channel();

                ByteBuffer buff = ByteBuffer.allocate(1024);
                String content = "";
                while(sc.read(buff) > 0)
                {
                    buff.flip();
                    content += charset.decode(buff);
                }
                //若系统发送通知名字已经存在，则需要换个昵称
                if(USER_EXIST.equals(content)) {
                    name = "";
                }
                System.out.println(content);
                sk.interestOps(SelectionKey.OP_READ);
            }
        }
    }

    public static void main(String[] args) throws IOException
    {
        new ChatRoomClient().init();
    }

}
