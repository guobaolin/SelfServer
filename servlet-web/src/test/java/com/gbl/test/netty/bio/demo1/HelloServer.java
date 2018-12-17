package com.gbl.test.netty.bio.demo1;

import com.gbl.entity.User;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by guobaolin on 2018/12/17.
 */
public class HelloServer {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int port = 8082;
        ServerSocket serverSocket = new ServerSocket(port, 50);
//        serverSocket.bind(new InetSocketAddress(port), 20);
        System.out.println("服务端已经准备就绪.....");
        boolean accept = true;
        while (accept) {
            Socket socket = serverSocket.accept();
            System.out.println("连接过来的客户端：" + socket.getInetAddress());
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            ObjectInputStream objectInputStream = new ObjectInputStream(in);
            User user = (User) objectInputStream.readObject();
            System.out.println(user.toString());  //打印对象
            user.setName("hello " + user.getName());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
            objectOutputStream.writeObject(user);
        }

    }

}
