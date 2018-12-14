package com.gbl;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Arrays;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        try {
//            InetAddress[] address = InetAddress.getAllByName("www.baidu.com");
//            Arrays.stream(address).forEach(System.out::println);

            byte[] ip1 = new byte[]{(byte)192,(byte)168,(byte)224,(byte)1};
//            byte[] ip2 = new byte[]{(byte)192,(byte)168,(byte)224,(byte)2};

            InetAddress inetAddress = InetAddress.getByAddress("www.gbl.com",ip1);
            System.out.println(inetAddress.toString());

//            InetSocketAddress socketAddress = InetSocketAddress.createUnresolved("localhost", 90);
//            System.out.println(socketAddress.toString());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void webspider(){
        try {
            URL url = new URL("https://channel.jd.com/men.html");
            InputStream inputStream = url.openStream();
            InputStreamReader isr = new InputStreamReader(inputStream, "GBK");
            BufferedReader br = new BufferedReader(isr);
            String string;
            while ((string=br.readLine())!=null){
                System.out.println(string);
            }
            br.close();
            isr.close();
            inputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
