package com.gbl.test.io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by guobaolin on 2018/12/7.
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 8888;
        if (args != null && args.length > 0){
            try{
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){
                // 采用默认值
            }
        }

        try(ServerSocket server = new ServerSocket(port)) {
            System.out.println("The time server is start in port:" + port);
            Socket socket = null;
            while (true){
                socket = server.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
