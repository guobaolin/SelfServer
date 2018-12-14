package com.gbl.controller;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RocketMQController {

    @Autowired
    private DefaultMQProducer defaultMQProducer;

    @Autowired
    private DefaultMQPushConsumer defaultMQPushConsumer;

    @RequestMapping("/sendMessage")
    public String sendMessage(String message) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        Message msg = new Message("TEST-TOPIC",
                "TEST-TAG",
                "keys",
                message.getBytes());
        SendResult result = defaultMQProducer.send(msg);
        return result.toString();
    }

    @RequestMapping("/pushMessage")
    public String pullMessage(){

//        defaultMQPushConsumer.getMessage
        return "";
    }
}
