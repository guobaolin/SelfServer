package com.gbl;

import com.gbl.concurrent.semaphore.Car;
import com.gbl.concurrent.semaphore.Driver;
import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
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

        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(this.getClass());
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        String msg = "{\"session_id\":\"ACCF23DF06AF\",\"from_cluster\":\"10.8.34.7:25861:1565346282472\",\"general_message\":{\"dataMode\":0,\"packet\":null,\"packetStart\":\"5A\",\"source\":0,\"developerID\":\"1\",\"msgId\":null,\"macAddress\":\"ACCF23DF06AF\",\"command\":\"0105\",\"sendTo\":\"11_1_1\",\"deviceType\":11,\"deviceSubType\":1,\"deviceBrand\":null,\"dataVersion\":4,\"packetSequence\":262832,\"direction\":0,\"bussinessType\":1,\"data\":{\"dataTimeStamp\":1565560361016,\"addtimeh\":0,\"orderBrightness\":0,\"Localmin\":52,\"addtimem\":0,\"out2\":0,\"remainPresetRuntimeH\":0,\"presetRuntimeM\":0,\"addtimemin\":0,\"out1\":0,\"Localhour\":13,\"mode\":0,\"presetOpenH\":0,\"timeCloseM\":0,\"workstate\":0,\"brightness\":71,\"_bindUserId\":88891306,\"remainingTimeM\":0,\"presetRuntimeH\":0,\"Localyear\":19,\"workmode\":0,\"resver2\":0,\"resver1\":0,\"remainingTimeH\":0,\"deviceId\":23592892,\"addcntm\":0,\"warring2\":0,\"Localday\":12,\"addcntl\":0,\"orderGreen\":0,\"warring1\":0,\"addcnth\":0,\"green\":169,\"Localmonth\":8,\"orderBlue\":0,\"remainPresetRuntimeM\":0,\"reserve1\":0,\"productId\":1755,\"runstate\":0,\"red\":252,\"blue\":108,\"remainPresetOpenM\":0,\"presetOpenM\":0,\"addrimel\":0,\"orderMist\":0,\"Localsec\":37,\"remainPresetOpenH\":0,\"timeCloseH\":0,\"orderRed\":0,\"mist\":0}}}";
        byte[] bytes = msg.getBytes();
        System.out.println(bytes.length);
    }

    @Test
    public void test2() {
        Integer integer = new Integer(0);
    }

}
