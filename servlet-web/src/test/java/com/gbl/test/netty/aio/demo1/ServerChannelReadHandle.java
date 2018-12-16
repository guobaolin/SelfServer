package com.gbl.test.netty.aio.demo1;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * Created by guobaolin on 2018/12/16.
 */
public class ServerChannelReadHandle implements CompletionHandler<Integer, StringBuffer> {

    private AsynchronousSocketChannel socketChannel;

    private ByteBuffer byteBuffer;

    public ServerChannelReadHandle(AsynchronousSocketChannel socketChannel, ByteBuffer byteBuffer) {
        this.socketChannel = socketChannel;
        this.byteBuffer = byteBuffer;
    }

    @Override
    public void completed(Integer result, StringBuffer attachment) {
        //如果条件成立，说明客户端主动终止了TCP套接字，这时服务端终止就可以了
        if (result == 1) {
            try {
                socketChannel.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

        System.out.println("completed(Integer result, Void attachment) : 然后我们来取出通道中准备好的值");

        byteBuffer.flip();
        byte[] contexts = new byte[1024];
        byteBuffer.get(contexts, 0, result);
        byteBuffer.clear();
        try {
            String nowContent = new String(contexts, 0, result, "UTF-8");
            attachment.append(nowContent);
            System.out.println("================目前的传输结果：" + attachment);
        } catch (UnsupportedEncodingException e) {
            System.err.println(e.getMessage());
        }

        if (attachment.indexOf("over") == 1) {
            return;
        } else {
            byteBuffer.clear();
            System.out.println("客户端发来的信息======message : " + attachment);

            //==============================================================
            //             接收完成后，可以在这里正式处理业务了
            //==============================================================

            //回发数据，并关闭channel
            ByteBuffer sendBuffer = null;
            try {
                sendBuffer = ByteBuffer.wrap(URLEncoder.encode("你好客户端,这是服务器的返回数据", "UTF-8").getBytes());
                socketChannel.write(sendBuffer);
                socketChannel.close();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            //===============================================================
            //          以"over"符号作为客户端完整信息的标记
            //===============================================================
            System.out.println("=======收到完整信息，开始处理业务=========");
            attachment = new StringBuffer();

            socketChannel.read(byteBuffer, attachment, this);
        }
    }

    @Override
    public void failed(Throwable exc, StringBuffer attachment) {
        System.out.println("=====发现客户端异常关闭，服务器将关闭TCP通道");
        try {
            socketChannel.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
