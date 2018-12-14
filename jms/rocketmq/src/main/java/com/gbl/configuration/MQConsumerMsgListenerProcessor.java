package com.gbl.configuration;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by guobaolin on 2018/10/18.
 */
@Component
public class MQConsumerMsgListenerProcessor implements MessageListenerConcurrently {
    private static final Logger logger = LoggerFactory.getLogger(MQConsumerMsgListenerProcessor.class);

    @Value("${rocketmq.consumer.topic}")
    private String topic;
    @Value("${rocketmq.consumer.tag}")
    private String tag;

    /**
     *  默认msgs里只有一条消息，可以通过设置consumeMessageBatchMaxSize参数来批量接收消息<br/>
     *  不要抛异常，如果没有return CONSUME_SUCCESS ，consumer会重新消费该消息，直到return CONSUME_SUCCESS
     */
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        if (CollectionUtils.isEmpty(msgs)){
            logger.info("接受到的消息为空，不处理，直接返回成功");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        MessageExt messageExt = msgs.get(0);
        logger.info("接受到的消息为：" + messageExt.toString());
        System.out.println("接受到的消息为：" + messageExt.toString());

        if (messageExt.getTopic().equals(topic) && messageExt.getTags().equals(tag)){
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }

        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
    }
}
