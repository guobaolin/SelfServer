package com.gbl.iot.mqttreceive1.config;

import com.gbl.iot.mqttreceive1.tools.MqttClientPropeties;
import com.gbl.iot.mqttreceive1.tools.MqttPushClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by guobaolin on 2019/11/4.
 */
@Configuration
@EnableConfigurationProperties(MqttClientPropeties.class)
public class MqttConfiguration {

    @Bean
    public MqttPushClient getMqttPushClient(MqttClientPropeties propeties) {
        return new MqttPushClient(propeties);
    }


}
