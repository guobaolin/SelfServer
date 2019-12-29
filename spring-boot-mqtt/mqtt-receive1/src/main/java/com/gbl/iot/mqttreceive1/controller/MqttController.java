package com.gbl.iot.mqttreceive1.controller;

import com.gbl.iot.mqttreceive1.tools.MqttPushClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by guobaolin on 2019/11/5.
 */
@RestController
@RequestMapping(value = "/mqtt")
public class MqttController {

    @Autowired
    private MqttPushClient mqttPushClient;

    @RequestMapping("publish")
    public String publish(String topic, String data) {
        mqttPushClient.publish(topic, data);
        return "publish success";
    }

    @RequestMapping("subscribe")
    public String subscribe(String topic) {
        mqttPushClient.subscribe(topic);
        return "subscribe success";
    }

    @RequestMapping("unsubscribe")
    public String unsubscribe(String topic) {
        mqttPushClient.unsubscribe(topic);
        return "unsubscribe success";
    }

}
