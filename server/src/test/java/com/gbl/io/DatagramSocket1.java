package com.gbl.io;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by guobaolin on 2018/12/3.
 */
public class DatagramSocket1 {

    public static void main(String[] args) throws IOException {
        java.net.DatagramSocket socket = new java.net.DatagramSocket(10086);
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        socket.receive(packet);
        String info = new String(data, 0, data.length);
        System.out.println("我是服务器，客户端告诉我" + info);

        InetAddress address = packet.getAddress();
        int port = packet.getPort();
        byte[] data2 = "欢迎您!".getBytes();
        DatagramPacket packet1 = new DatagramPacket(data2, data2.length, address, port);
        socket.send(packet1);
        socket.close();
    }
}
