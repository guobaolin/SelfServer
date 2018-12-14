package com.gbl.io;

import java.io.PipedOutputStream;

/**
 * Created by guobaolin on 2018/11/24.
 */
public class PipedSend extends Thread{
    private PipedOutputStream out = new PipedOutputStream();

    public PipedOutputStream getOut(){
        return out;
    }

    public void run() {
        String info = "hello, receiver";
        try{
            out.write(info.getBytes());
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
