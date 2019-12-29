package com.gbl.controller;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

//@RestController
public class RocketMQController {

    private static final Logger logger = LoggerFactory.getLogger(RocketMQController.class);

    //    @Autowired
    private DefaultMQProducer defaultMQProducer;
//
//    @Autowired
//    private DefaultMQPushConsumer defaultMQPushConsumer;

    @RequestMapping("/sendMessage")
    public String sendMessage(String message) throws InterruptedException, RemotingException, MQClientException, MQBrokerException, UnsupportedEncodingException {
        String topic = "GUO_BAO_LIN";
        String tags = "lin";
        String messages = message;
        String keys = UUID.randomUUID().toString().replace("-", "");
        Message msg = null;
        try {
            msg = new Message(topic, tags, keys, messages.getBytes(RemotingHelper.DEFAULT_CHARSET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        SendResult result = null;
        try {
//            SendCallback sendCallback = new SendCallback() {
//                @Override
//                public void onSuccess(SendResult sendResult) {
//                    System.out.println(sendResult);
//                }
//
//                @Override
//                public void onException(Throwable e) {
//                    System.out.println(e.getCause() + " " + e.getMessage());
//                }
//            };
//
//            defaultMQProducer.send(msg, sendCallback, 10);  //同步方式

            MessageQueue messageQueue = new MessageQueue();

            defaultMQProducer.sendOneway(msg);

        } catch (MQClientException | RemotingException | InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println(result.toString());
        return "rocketmq producer run...";
    }

    @RequestMapping("/pushMessage")
    public String pullMessage(){

//        defaultMQPushConsumer.getMessage


        return "";
    }
}
