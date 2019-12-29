package com.gbl;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.impl.consumer.PullResultExt;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by guobaolin on 2019/6/20.
 */
public class RocketMQConsumerTest {

    public static void main(String[] args) throws InterruptedException, MQClientException, IOException {
        RocketMQConsumerTest test = new RocketMQConsumerTest();
        test.defaultMQPushConsumerTest();
    }

    @Test
    public void defaultMQPushConsumerTest() throws MQClientException, InterruptedException, IOException {
        String groupName = "guobaolin-consumer";
        String namesrvAddr = "192.168.120.138:9876;192.168.120.129:9876";
        int consumeThreadMin = 1, consumeThreadMax = 3;
        String topic = "GUO_BAO_LIN";
        String tag = "bao";

        DefaultMQPushConsumer consumer1 = new DefaultMQPushConsumer(groupName);
        consumer1.setMessageModel(MessageModel.BROADCASTING);
        consumer1.setNamesrvAddr(namesrvAddr);
        consumer1.setInstanceName("A");
        consumer1.setConsumeThreadMin(consumeThreadMin);
        consumer1.setConsumeThreadMax(consumeThreadMax);
//        consumer1.registerMessageListener((MessageListenerConcurrently) (msgs, context)->{
//            if (CollectionUtils.isEmpty(msgs)){
//                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//            }
//            MessageExt messageExt = msgs.get(0);
//            System.out.println("接受到的消息为A：" + new String(messageExt.getBody()) + " -> " + messageExt.toString());
//            try {
//                Thread.sleep(1000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            if (messageExt.getTopic().equals(topic) && messageExt.getTags().equals(tag)){
//                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//            }else {
//                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
//            }
//        });

        consumer1.registerMessageListener(new MessageListenerOrderly() {

            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                // 设置自动提交
                context.setAutoCommit(true);
                for (MessageExt msg : msgs) {
                    System.out.println(msg + ",内容：" + new String(msg.getBody()));
                }

                try {
                    TimeUnit.SECONDS.sleep(5L);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        consumer1.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer1.subscribe(topic, tag);
        consumer1.start();

//        DefaultMQPushConsumer consumer2 = new DefaultMQPushConsumer(groupName);
//        consumer2.setNamesrvAddr(namesrvAddr);
//        consumer2.setInstanceName("B");
//        consumer2.setConsumeThreadMin(consumeThreadMin);
//        consumer2.setConsumeThreadMax(consumeThreadMax);
//        consumer2.registerMessageListener((MessageListenerOrderly) (msgs, context)->{
//            if (CollectionUtils.isEmpty(msgs)){
//                return ConsumeOrderlyStatus.SUCCESS;
//            }
//            MessageExt messageExt = msgs.get(0);
//            System.out.println("接受到的消息为B：" + new String(messageExt.getBody()) + " -> " + messageExt.toString());
//            try {
//                Thread.sleep(1000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            if (messageExt.getTopic().equals(topic) && messageExt.getTags().equals(tag)){
//                return ConsumeOrderlyStatus.SUCCESS;
//            }else {
//                return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
//            }
//        });
//        consumer2.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
//        consumer2.setMessageModel(MessageModel.BROADCASTING);
//        consumer2.subscribe(topic, tag);
//        consumer2.start();

        System.in.read();
    }

    @Test
    public void consumer() throws MQClientException, IOException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("order_Consumer");
        consumer.setNamesrvAddr("192.168.120.137:9876");

        /**
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费<br>
         * 如果非第一次启动，那么按照上次消费的位置继续消费
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        consumer.subscribe("GUO_BAO_LIN", "guo");

        consumer.registerMessageListener(new MessageListenerOrderly() {
            AtomicLong consumeTimes = new AtomicLong(0);

            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                // 设置自动提交
                context.setAutoCommit(true);
                for (MessageExt msg : msgs) {
                    System.out.println(msg + ",内容：" + new String(msg.getBody()));
                }

                try {
                    TimeUnit.SECONDS.sleep(5L);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }

                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        consumer.start();

        System.out.println("Consumer1 Started.");
        System.in.read();
    }

    @Test
    public void defaultMQPullConsumerTest() throws MQClientException {
        final Map<MessageQueue, Long> offsetTable = new HashMap<>();
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("guobaolin-pull-consumer");
        consumer.setNamesrvAddr("192.168.120.129:9876");
        consumer.start();

        try {
            Set<MessageQueue> mqs = consumer.fetchSubscribeMessageQueues("GUO_BAO_LIN");
            for (MessageQueue mq :
                    mqs) {
                System.out.println("Consume from the queue:" + mq);
                long offset = offsetTable.get(mq) == null ? 0 : offsetTable.get(mq);
                PullResultExt pullResult = (PullResultExt) consumer.pullBlockIfNotFound(mq, "guo", offset, 32);
                offsetTable.put(mq, pullResult.getNextBeginOffset());
                switch (pullResult.getPullStatus()) {
                    case FOUND:
                        List<MessageExt> messageExts = pullResult.getMsgFoundList();
                        messageExts.stream().map(msg -> new String(msg.getBody())).forEach(System.out::println);
                        break;
                    case NO_MATCHED_MSG:
                        break;
                    case NO_NEW_MSG:
                        break;
                    case OFFSET_ILLEGAL:
                        break;
                    default:
                        break;
                }
            }
        } catch (MQClientException e) {

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDefaultMQPushConsumer() throws MQClientException, IOException {
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
//        consumer.setConsumeThreadMax(128);
//        consumer.setPullBatchSize(50);
        consumer.setConsumeMessageBatchMaxSize(50);
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.println(new String(msg.getBody()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.out.println("Consumer Started.");
        System.in.read();
    }
}
