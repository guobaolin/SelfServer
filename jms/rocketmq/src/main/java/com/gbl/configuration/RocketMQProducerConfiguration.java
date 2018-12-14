package com.gbl.configuration;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.gbl.exception.RocketMQException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RocketMQProducerConfiguration {

    @Value("${rocketmq.producer.groupName}")
    private String groupName;
    @Value("${rocketmq.producer.namesrvAddr}")
    private String namesrvAddr;
    @Value("${rocketmq.producer.instanceName}")
    private String instanceName;
    @Value("${rocketmq.producer.maxMessageSize}")
    private int maxMessageSize ; //4M
    @Value("${rocketmq.producer.sendMsgTimeout}")
    private int sendMsgTimeout ;

    @Bean(destroyMethod = "shutdown")
    public DefaultMQProducer getRocketMQProducer() throws RocketMQException{
        if (StringUtils.isBlank(groupName)){
            throw new RocketMQException("groupName is blank");
        }
        if (StringUtils.isBlank(namesrvAddr)){
            throw new RocketMQException("nameServerAddr is blank");
        }
        if (StringUtils.isBlank(instanceName)){
            throw new RocketMQException("instanceName is blank");
        }
        DefaultMQProducer producer = new DefaultMQProducer(groupName);
        producer.setNamesrvAddr(namesrvAddr);
        producer.setInstanceName(instanceName);
        producer.setMaxMessageSize(maxMessageSize);
        producer.setSendMsgTimeout(sendMsgTimeout);

        try {
            producer.start();
            System.err.println(String.format("producer is start ! groupName:[%s],namesrvAddr:[%s]",
                    groupName, namesrvAddr));
        } catch (MQClientException e) {
            throw new RocketMQException(e);
        }
        return producer;
    }

}
