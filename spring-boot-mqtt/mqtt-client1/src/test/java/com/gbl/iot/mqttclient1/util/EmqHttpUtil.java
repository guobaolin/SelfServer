package com.gbl.iot.mqttclient1.util;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by guobaolin on 2019/11/4.
 */
public class EmqHttpUtil {
    private static final Logger logger = LoggerFactory.getLogger(EmqHttpUtil.class);

    public static MqttClient mqttClient = null;
    private static MemoryPersistence memoryPersistence = null;
    private static MqttConnectOptions mqttConnectOptions = null;

    private static EmqHttpUtil instance = null;


    public EmqHttpUtil() {
        init("admin");
    }

    public static EmqHttpUtil getInstance() throws Exception {
        if (instance == null) {
            synchronized (EmqHttpUtil.class) {
                if (instance == null) {
                    instance = new EmqHttpUtil();
                }
            }
        }
        return instance;
    }

    public void init(String clientId) {
        // 初始化连接设置对象
        mqttConnectOptions = new MqttConnectOptions();
        // 初始化MqttClient
        if (mqttConnectOptions != null) {
            // true可以安全地使用内存持久性作为客户端断开连接时清除的所有状态
            mqttConnectOptions.setCleanSession(true);
            // 设置连接超时
            mqttConnectOptions.setConnectionTimeout(30);
            // 设置持久化方式
            memoryPersistence = new MemoryPersistence();
            if (memoryPersistence != null && clientId != null) {
                try {
                    String serverURI = "tcp://192.168.120.140:1883";
                    mqttClient = new MqttClient(serverURI, clientId, memoryPersistence);
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }

        } else {
            logger.error("MqttConnectOptions对象为空");
        }

        // 设置连接和回调
        if (mqttClient != null) {
            if (!mqttClient.isConnected()) {
                try {
                    logger.info("创建连接:" + mqttClient.isConnected());
                    mqttClient.connect(mqttConnectOptions);
                } catch (MqttSecurityException e) {
                    e.printStackTrace();
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }
        } else {
            logger.error("mqttClient为空");
        }
    }

    public void closeConnect() {
        // 关闭存储方式
        if (memoryPersistence != null) {
            try {
                memoryPersistence.close();
            } catch (MqttPersistenceException e) {
                e.printStackTrace();
            }
        } else {
            logger.error("memoryPersistence is null");
        }

        // 关闭连接
        if (mqttClient != null) {
            if (mqttClient.isConnected()) {
                try {
                    mqttClient.disconnect();
                    mqttClient.close();
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            } else {
                logger.error("mqttClient is not connect");
            }
        } else {
            logger.error("mqttClient is null");
        }
    }

    // 发布消息
    public void publishMessage(String pubTopic, String message, int qos) {
        if (mqttClient != null && mqttClient.isConnected()) {
            MqttMessage mqttMessage = new MqttMessage();
            mqttMessage.setQos(qos);
            mqttMessage.setPayload(message.getBytes());
            MqttTopic topic = mqttClient.getTopic(pubTopic);
            if (topic != null) {
                try {
                    MqttDeliveryToken publish = topic.publish(mqttMessage);
                    if (!publish.isComplete()) {
                        logger.info("消息发布成功");

                    }
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }
        } else {
            reConnect();
        }
    }


    public void reConnect() {
        if (mqttClient != null) {
            if (!mqttClient.isConnected()) {
                if (mqttConnectOptions != null) {
                    try {
                        mqttClient.connect(mqttConnectOptions);
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                } else {
                    logger.error("mqttConnectOptions is null");
                }
            } else {
                logger.error("mqttClient is null or connect");
            }
        } else {
            init("admin");
        }
    }

    // 订阅主题
    public void subTopic(String topic) {
        if (mqttClient != null && mqttClient.isConnected()) {
            try {
                mqttClient.subscribe(topic, 1);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        } else {
            logger.error("mqttClient is error");
        }
    }

    // 清空主题
    public void cleanTopic(String topic) {
        if (mqttClient != null && !mqttClient.isConnected()) {
            try {
                mqttClient.unsubscribe(topic);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        } else {
            logger.error("mqttClient is error");
        }
    }


    @Test
    public void publishMessage() {
        String pubTopic = "marketAll";
        String message = "hello, emq";
        EmqHttpUtil emqHttpUtil = new EmqHttpUtil();
        emqHttpUtil.publishMessage(pubTopic, message, 1);
    }

    @Test
    public void subscribe() {
        String pubTopic = "marketAll";
        EmqHttpUtil emqHttpUtil = new EmqHttpUtil();
        mqttClient.setCallback(new PushCallback());
        emqHttpUtil.subTopic(pubTopic);
    }

}
