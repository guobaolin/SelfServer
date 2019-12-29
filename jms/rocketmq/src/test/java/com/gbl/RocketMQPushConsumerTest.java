package com.gbl;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.io.IOException;
import java.util.List;

/**
 * Created by guobaolin on 2019/8/5.
 */
public class RocketMQPushConsumerTest {

    public static void main(String[] args) throws MQClientException, IOException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("alp_device_data");
        consumer.setNamesrvAddr("10.8.9.22:9876;10.8.34.6:9876");
        consumer.setInstanceName(String.valueOf(System.currentTimeMillis()));
        /**
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费<br>
         * 如果非第一次启动，那么按照上次消费的位置继续消费
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("ALP_RUN_DATA_TOPIC", "*");
        consumer.setMessageModel(MessageModel.CLUSTERING);
        consumer.setMaxReconsumeTimes(2);
        consumer.setPullBatchSize(50);
        consumer.setConsumeMessageBatchMaxSize(50);
        consumer.registerMessageListener(new MessageListenerOrderly() {
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.println(new String(msg.getBody()));
                }

                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        consumer.start();
        System.out.println("Consumer Started.");
        System.in.read();
    }
}
