package com.gbl.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.context.support.XmlWebApplicationContext;

import java.util.*;

/**
 * Created by guobaolin on 2018/11/17.
 */
public class Test {

    @org.junit.Test
    public void resourceTest() {

        Resource resource = new ClassPathResource("");

        XmlWebApplicationContext xwc = new XmlWebApplicationContext();
        xwc.refresh();

    }

    @org.junit.Test
    public void testSimpleLoad() {
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("spring-application.xml"));
        MyTestBean myTestBean = (MyTestBean) beanFactory.getBean("myTestBean");
        MyTestBean myTestBean2 = (MyTestBean) beanFactory.getBean("myTestBean");
        System.out.println(myTestBean);
        System.out.println(myTestBean2);
    }

    @org.junit.Test
    public void testDate() {
        Date date = new Date();
        System.out.println(date);
    }

    @org.junit.Test
    public void test2() {
        String msg = "\"session_id\":\"4CCCCCC18552\",\"from_cluster\":\"10.7.0.53:25861:1565948149182\",\"general_message\":{\"dataMode\":1,\"packet\":null,\"packetStart\":\"5A\",\"source\":0,\"developerID\":\"1\",\"msgId\":null,\"macAddress\":\"4CCCCCC18552\",\"command\":\"0105\",\"sendTo\":\"6_3_1\",\"deviceType\":6,\"deviceSubType\":3,\"deviceBrand\":null,\"dataVersion\":1,\"packetSequence\":4228,\"direction\":0,\"bussinessType\":1,\"data\":{\"P2BHeartRate\":0,\"dataTimeStamp\":1566157766834,\"P4BBreathRate\":0,\"P2ALinkStatus\":1,\"P1NetworkStatus\":\"114\",\"P1AHasAnybody\":0,\"P3ATurnOverTimes\":0,\"P3BActivityEnergy\":0,\"P5ABreathRate\":0,\"P5AHeartRate\":0,\"P4Month\":2,\"P2BHasAnybody\":0,\"P5OximeterPower\":0,\"P3OximeterPulse\":0,\"P4AHeartRate\":0,\"P1ABreathRate\":0,\"P4Second\":49,\"P5BActivityEnergy\":0,\"P2OximeterPower\":0,\"P1Year\":19,\"P3AActivityEnergy\":0,\"P2OximeterPulse\":0,\"P1OximeterPerfusion\":0,\"P5ALinkStatus\":1,\"P4OximeterSaturation\":0,\"P5OximeterPerfusion\":0,\"P5Year\":19,\"P3Minute\":9,\"P3AHeartRate\":0,\"P1SDStatus\":0,\"P2Year\":19,\"P1Date\":22,\"P3Hour\":0,\"P3OximeterLink\":0,\"P4OximeterPower\":0,\"P1ATurnOverTimes\":0,\"P1OximeterLink\":0,\"P2Minute\":9,\"P4ALinkStatus\":1,\"P1OximeterSaturation\":0,\"P4BatteryPower\":100,\"P3ALinkStatus\":1,\"P3BBreathRate\":0,\"P1BBreathRate\":0,\"P2Date\":22,\"P3Year\":19,\"P4ATurnOverTimes\":0,\"P5OximeterSaturation\":0,\"P1Minute\":9,\"P1BHeartRate\":0,\"P5AHasAnybody\":0,\"P2BBreathRate\":0,\"P2OximeterPerfusion\":0,\"P1OximeterDataType\":0,\"P4Date\":22,\"P2AActivityEnergy\":0,\"P5BTurnOverTimes\":0,\"P3Month\":2,\"P3OximeterPerfusion\":0,\"P3AHasAnybody\":0,\"P2Second\":47,\"P4OximeterDataType\":0,\"P1Hour\":0,\"P4Minute\":9,\"P5Minute\":9,\"P2OximeterSaturation\":0,\"P3SDStatus\":0,\"P1ALinkStatus\":1,\"P1Month\":2,\"P2SDStatus\":0,\"P4NetworkStatus\":\"114\",\"P4BHeartRate\":0,\"P5BHasAnybody\":0,\"P1BActivityEnergy\":0,\"P3BHasAnybody\":0,\"P1AHeartRate\":0,\"P2OximeterDataType\":0,\"P2BatteryPower\":100,\"P5BatteryPower\":100,\"P3BHeartRate\":0,\"P4ABreathRate\":0,\"P2AHeartRate\":0,\"P5SDStatus\":0,\"P5Second\":50,\"_bindUserId\":0,\"P1OximeterPower\":0,\"P4Year\":19,\"P2ABreathRate\":0,\"P3OximeterDataType\":0,\"P4SDStatus\":0,\"P4AHasAnybody\":0,\"deviceId\":12561358,\"P5OximeterLink\":0,\"P3OximeterSaturation\":0,\"P4AActivityEnergy\":0,\"P4BHasAnybody\":0,\"P5Month\":2,\"P3BLinkStatus\":0,\"P5BHeartRate\":0,\"P4BTurnOverTimes\":0,\"TotalPacketNum\":5,\"P3ABreathRate\":0,\"P5AActivityEnergy\":0,\"P1BTurnOverTimes\":0,\"P1BatteryPower\":100,\"P5Hour\":0,\"P4BActivityEnergy\":0,\"P2BActivityEnergy\":0,\"P5OximeterPulse\":0,\"P1Second\":46,\"P4Hour\":0,\"P1BLinkStatus\":0,\"P3NetworkStatus\":\"114\",\"P5NetworkStatus\":\"114\",\"P5Date\":22,\"P1BHasAnybody\":0,\"P1AActivityEnergy\":0,\"P5OximeterDataType\":0,\"P3BatteryPower\":100,\"P2ATurnOverTimes\":0,\"P2BTurnOverTimes\":0,\"P2Hour\":0,\"P3BTurnOverTimes\":0,\"P3OximeterPower\":0,\"P5BBreathRate\":0,\"P3Date\":22,\"P2AHasAnybody\":0,\"P2Month\":2,\"P2OximeterLink\":0,\"P3Second\":48,\"P5ATurnOverTimes\":0,\"P4OximeterPerfusion\":0,\"productId\":139,\"P2BLinkStatus\":0,\"P4OximeterPulse\":0,\"P2NetworkStatus\":\"114\",\"P5BLinkStatus\":0,\"P4OximeterLink\":0,\"P1OximeterPulse\":0,\"P4BLinkStatus\":0}}}";
        byte[] bytes = msg.getBytes();
        System.out.println(bytes.length);
    }

    @org.junit.Test
    public void test3() {
        List<String> stringList = Collections.synchronizedList(new ArrayList<>());
        stringList.add("111");
        stringList.add("222");
        stringList.add("333");
        stringList.add("444");
        stringList.add("555");
        stringList.add("666");

        Thread thread = new Thread(() -> {
            synchronized (stringList) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(stringList);
            }
        });
        thread.start();

        stringList.add("777");

        System.out.println(stringList);

    }

    @org.junit.Test
    public void test4() {
        Map<String, Object> data = new HashMap<>();

        Integer productId = (Integer) data.get("productId");

        System.out.println(productId);
    }

}
