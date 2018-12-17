package com.gbl.test.netty.bio.demo1;

import com.gbl.entity.User;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by guobaolin on 2018/12/17.
 */
public class HelloClient {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int port = 8083;
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("10.8.9.85", port));
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
        User user = new User();
        user.setId(1);
        user.setName("world");
        user.setAge("18");
        user.setAddress("shenzhen");

        objectOutputStream.writeObject(user);

        ObjectInputStream objectInputStream = new ObjectInputStream(in);
        User user1 = (User) objectInputStream.readObject();
        System.out.println(user1.toString());
    }
}
