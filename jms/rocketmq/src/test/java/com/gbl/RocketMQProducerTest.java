package com.gbl;


import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;

public class RocketMQProducerTest {

    @Test
    public void producer() throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupTest");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();

        Message msg = new Message("topic-test", "测试消息".getBytes());
        SendResult result = producer.send(msg);
        System.out.println(result);
    }

//    @Test
//    public void producer2() throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
//        DefaultMQProducer producer = new DefaultMQProducer("producerGroupName2");
//        producer.setNamesrvAddr("127.0.0.1:9876");
//        producer.start();
//        Message msg = new Message("topic-test", "测试消息".getBytes());
//        SendResult sendResult = producer.send(msg);
//        System.out.println(sendResult);
//        producer.shutdown();
//    }
}
