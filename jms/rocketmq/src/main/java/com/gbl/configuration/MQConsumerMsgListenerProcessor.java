package com.gbl.configuration;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by guobaolin on 2018/10/18.
 */
@Component
public class MQConsumerMsgListenerProcessor implements MessageListenerConcurrently {
    private static final Logger logger = LoggerFactory.getLogger(MQConsumerMsgListenerProcessor.class);

    /**
     *  默认msgs里只有一条消息，可以通过设置consumeMessageBatchMaxSize参数来批量接收消息<br/>
     *  不要抛异常，如果没有return CONSUME_SUCCESS ，consumer会重新消费该消息，直到return CONSUME_SUCCESS
     */
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext consumeConcurrentlyContext) {

        for (MessageExt message : msgs) {
            System.out.println(new String(message.getBody()));
        }

        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
