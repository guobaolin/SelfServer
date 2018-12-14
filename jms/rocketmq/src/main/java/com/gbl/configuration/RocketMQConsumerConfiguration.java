package com.gbl.configuration;

import com.gbl.exception.RocketMQException;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RocketMQConsumerConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(RocketMQConsumerConfiguration.class);

    @Value("${rocketmq.consumer.namesrvAddr}")
    private String namesrvAddr;
    @Value("${rocketmq.consumer.groupName}")
    private String groupName;
    @Value("${rocketmq.consumer.topic}")
    private String topic;
    @Value("${rocketmq.consumer.tag}")
    private String tag;
    @Value("${rocketmq.consumer.consumeThreadMin}")
    private int consumeThreadMin;
    @Value("${rocketmq.consumer.consumeThreadMax}")
    private int consumeThreadMax;

    @Autowired
    private MQConsumerMsgListenerProcessor mqConsumerMsgListenerProcessor;

    @Bean
    public DefaultMQPushConsumer getRocketMQConsumer() throws RocketMQException {
        if (StringUtils.isEmpty(groupName)) {
            throw new RocketMQException("groupName is null !!!");
        }

        if (StringUtils.isEmpty(namesrvAddr)) {
            throw new RocketMQException("namesrvAddr is null !!!");
        }

        if (StringUtils.isEmpty(topic)) {
            throw new RocketMQException("topic is null !!!");
        }

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setConsumeThreadMin(consumeThreadMin);
        consumer.setConsumeThreadMax(consumeThreadMax);
        consumer.registerMessageListener(mqConsumerMsgListenerProcessor);
        /**
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
         * 如果非第一次启动，那么按照上次消费的位置继续消费
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        /**
         * 设置消费模型，集群还是广播，默认为集群
         */
//        consumer.setMessageModel(MessageModel.CLUSTERING);
        /**
         * 设置一次消费消息的条数，默认为1条
         */
//        consumer.setConsumeMessageBatchMaxSize(consumeMessageBatchMaxSize);

        try {
            consumer.subscribe(topic, tag);
            consumer.start();
            logger.info("consumer is start !!! groupName:{}, topic:{}, namesrvAddr:{}", groupName, topic, namesrvAddr);
        } catch (MQClientException e) {
            logger.error("consumer is start !!! groupName:{}, topic:{}, namesrvAddr:{}", groupName, topic, namesrvAddr);
            throw new RocketMQException(e);
        }
        return consumer;
    }


}
