package com.gbl.iot.mqttclient1.util;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.junit.Test;

/**
 * Created by guobaolin on 2019/11/4.
 */
public class ClientMQTT {

    public static final String HOST = "tcp://192.168.120.140:1883";
    public static final String TOPIC1 = "marketAll";
    public static final String clientId = "admin";
    private MqttClient client;
    private MqttConnectOptions options;
    private String userName = "admin";
    private String password = "public";

    private void start() {
        try {
            client = new MqttClient(HOST, clientId, new MemoryPersistence());
            options = new MqttConnectOptions();
            options.setCleanSession(true);
            options.setUserName(userName);
            options.setPassword(password.toCharArray());
            options.setConnectionTimeout(10);
            options.setKeepAliveInterval(20);
            client.setCallback(new PushCallback());
            MqttTopic topic = client.getTopic(TOPIC1);
            options.setWill(topic, "close".getBytes(), 2, true);
            client.connect(options);
            client.subscribe(TOPIC1, 1);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() throws InterruptedException {
        ClientMQTT client = new ClientMQTT();
        client.start();
        Thread.sleep(1000L);
    }

}
