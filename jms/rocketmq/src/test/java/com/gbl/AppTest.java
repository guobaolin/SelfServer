package com.gbl;

import com.gbl.concurrent.semaphore.Car;
import com.gbl.concurrent.semaphore.Driver;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class AppTest {

    @Test
    public void shouldAnswerWithTrue() throws MalformedURLException {
        String urlStr = "http://fileserver1.clife.net:8981/";

        String path = "http://fileserver1.clife.net:8080/group1/M00/AF/46/Cvtlp1vJUNiAK2xCAAU7smVdZkE2029.js";
//        https://fileserver1.clife.net/group1/M00/AF/4F/Cvtlp1vJg96AbcrcAARIi-5wuPQ1298.js

        URL url = new URL(urlStr);

        String regStr = String.format("^.*(%s).*(/group.*)$", url.getHost());

//        String reg = "^.*(://.*:).*(/group.*)$";
        String path1 = path.replaceAll(regStr, "https://$1$2");
        System.out.println(path1);

//        String path2 = path.replace("http", "https").replaceAll(":(8981|8080)", "");
//        System.out.println(path2);
    }

    @Test
    public void driver(){
        Driver driver = new Driver();
        for (int i = 0; i < 9; i++) {
            new Car(driver).start();
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test0(){
        String s = "guobaolin";

        assert s.equals("gbl");
    }

}
