package com.gbl.iot.mqttreceive1.tools;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by guobaolin on 2019/11/4.
 */
public class MqttPushClient {
    private static final Logger logger = LoggerFactory.getLogger(MqttPushClient.class);

    private MqttClientPropeties propeties;

    private MqttClient client;

    public MqttPushClient(MqttClientPropeties propeties) {
        logger.info("Connect MQTT：" + this);
        this.propeties = propeties;
        connect();
    }

    private void connect() {
        try {
            client = new MqttClient(propeties.getHost(), propeties.getClientId(), new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(propeties.isCleanSession());
            options.setUserName(propeties.getUserName());
            options.setPassword(propeties.getPassword().toCharArray());
            options.setConnectionTimeout(propeties.getTimeout());
            options.setKeepAliveInterval(propeties.getKeepalive());
            options.setAutomaticReconnect(propeties.isReconnect());

            client.setCallback(new PushCallback());
            client.connect(options);
        } catch (MqttException e) {
            logger.error("Connect MQTT error, cause:{}", e.getMessage());
        }
    }

    /**
     * 发布主题，用于通知<\p>
     * 默认qos为1, 非持久化
     *
     * @param topic
     * @param data
     */
    public void publish(String topic, String data) {
        publish(topic, data, 1, false);
    }

    /**
     * 发布消息
     *
     * @param topic
     * @param data
     * @param qos
     * @param retained
     */
    public void publish(String topic, String data, int qos, boolean retained) {
        MqttMessage message = new MqttMessage();
        message.setQos(qos);
        message.setRetained(retained);
        message.setPayload(data.getBytes());
        MqttTopic mqttTopic = client.getTopic(topic);
        if (null == mqttTopic) {
            logger.error("Topic not exist");
        } else {

            MqttDeliveryToken token;
            try {
                token = mqttTopic.publish(message);
                token.waitForCompletion();
            } catch (MqttException e) {
                logger.error("MqttTopic publish error, cause:{}", e.getMessage());
            }
        }
    }

    /**
     * 订阅某个主题 qos默认为1
     *
     * @param topic
     */
    public void subscribe(String topic) {
        subscribe(topic, 1);
    }

    /**
     * 订阅某个主题
     *
     * @param topic
     * @param qos
     */
    public void subscribe(String topic, int qos) {
        try {
            client.subscribe(topic, qos);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void unsubscribe(String topic) {
        try {
            client.unsubscribe(topic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

}
