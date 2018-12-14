package com.gbl.io;

import java.io.PipedInputStream;

/**
 * Created by guobaolin on 2018/11/24.
 */
public class PipedReader extends Thread {

    private PipedInputStream in = new PipedInputStream();

    public PipedInputStream getIn(){
        return in;
    }

    public void run() {
        byte[] info = new byte[1024];
        try{
            int len = in.read(info);
            System.out.println("receive from send : " + new String(info, 0, len));
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
