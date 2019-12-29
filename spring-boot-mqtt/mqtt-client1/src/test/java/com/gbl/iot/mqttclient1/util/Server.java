package com.gbl.iot.mqttclient1.util;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.junit.Test;

/**
 * Created by guobaolin on 2019/11/4.
 */
public class Server {
    public static final String HOST = "tcp://192.168.120.140:1883";

    public static final String TOPIC = "gbl";

    private static final String clientId = "server";

    private MqttClient client;
    private MqttTopic topic;
    private String userName = "admin";
    private String password = "public";

    private MqttMessage message;

    public Server() throws MqttException {
        // MemorvPersistence设置clientId的保存形式，默认为以内存保存
        client = new MqttClient(HOST, clientId, new MemoryPersistence());
        connect();
    }

    private void connect() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setUserName(userName);
        options.setPassword(password.toCharArray());
        // 设置超时时间
        options.setConnectionTimeout(10);
        // 设置会话心跳时间
        options.setKeepAliveInterval(20);

        try {
            client.setCallback(new PushCallback());
            client.connect(options);
            topic = client.getTopic(TOPIC);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void publish(MqttMessage message) throws MqttException {
        MqttDeliveryToken token = topic.publish(message);
        token.waitForCompletion();
        System.out.println(token.isComplete() + "===============");
    }

    @Test
    public void test() throws MqttException {
        Server server = new Server();
        server.message = new MqttMessage();
        server.message.setQos(1);
        server.message.setRetained(true);
        server.message.setPayload("Hello mqtt, i am programmer".getBytes());
        server.publish(server.message);
        try {
            Thread.sleep(100000L);
            System.out.println(server.message.isRetained() + "-------retained状态");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
