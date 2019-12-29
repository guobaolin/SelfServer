package com.gbl;


import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.List;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class RocketMQProducerTest {

    //    @Autowired
    private DefaultMQProducer defaultMQProducer;

    @Test
    public void producer() throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        String topic = "GUO_BAO_LIN";
        List<MessageQueue> messageQueues = defaultMQProducer.fetchPublishMessageQueues(topic);
        messageQueues.forEach(System.out::println);
    }

    @Test
    public void producerTest() throws UnsupportedEncodingException, InterruptedException, RemotingException, MQClientException, MQBrokerException {
        //MessageQueue [topic=GUO_BAO_LIN, brokerName=broker-b, queueId=1]
        String message = "测试指定MessageQueue生产消息12345";
        String topic = "GUO_BAO_LIN";
        String tags = "bao";
//        String keys = UUID.randomUUID().toString().replace("-", "");
        Message msg = new Message(topic, tags, message.getBytes(RemotingHelper.DEFAULT_CHARSET));
//        msg.setDelayTimeLevel(2);

//        MessageQueue messageQueue = new MessageQueue(topic,"broker-b",2);

        MessageQueueSelector selector = (mqs, msgs, arg) -> {
            int id = Integer.parseInt(arg.toString());
            int idMainIndex = id % 100;
            int size = mqs.size();
            int index = idMainIndex % size;
            return mqs.get(index);
        };

        SendResult sendResult = defaultMQProducer.send(msg, selector, 2);

        System.out.println(sendResult);
    }

    @Test
    public void producerTest1() throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        String groupName = "clife-devicedata-producer-gbl";
        String namesrvAddr = "10.8.16.73:9876;10.8.16.86:9876";
        String instanceName = "clife-devicedata-instanceName-gbl";
        int maxMessageSize = 131072, sendMsgTimeout = 10000;

//        String message = "{\"session_id\":\"405EE11002A4\",\"from_cluster\":\"10.104.228.51:25861:1562588851383\",\"general_message\":{\"dataMode\":1,\"packet\":null,\"packetStart\":\"5A\",\"source\":0,\"developerID\":\"1\",\"msgId\":null,\"macAddress\":\"405EE11002A4\",\"command\":\"0105\",\"sendTo\":\"6_3_1\",\"deviceType\":6,\"deviceSubType\":3,\"deviceBrand\":null,\"dataVersion\":1,\"packetSequence\":236282,\"direction\":0,\"bussinessType\":1,\"data\":{\"P2BHeartRate\":0,\"dataTimeStamp\":1564078777526,\"P4BBreathRate\":0,\"P2ALinkStatus\":1,\"P1NetworkStatus\":118,\"P1AHasAnybody\":0,\"P3ATurnOverTimes\":0,\"P3BActivityEnergy\":0,\"P5ABreathRate\":0,\"P5AHeartRate\":0,\"P4Month\":7,\"P2BHasAnybody\":0,\"P5OximeterPower\":0,\"P3OximeterPulse\":0,\"P4AHeartRate\":0,\"P1ABreathRate\":0,\"P4Second\":37,\"P5BActivityEnergy\":0,\"P2OximeterPower\":0,\"P1Year\":19,\"P3AActivityEnergy\":0,\"P2OximeterPulse\":0,\"P1OximeterPerfusion\":0,\"P5ALinkStatus\":1,\"P4OximeterSaturation\":0,\"P5OximeterPerfusion\":0,\"P5Year\":19,\"P3Minute\":19,\"P3AHeartRate\":0,\"P1SDStatus\":0,\"P2Year\":19,\"P1Date\":26,\"P3Hour\":10,\"P3OximeterLink\":0,\"P4OximeterPower\":0,\"P1ATurnOverTimes\":0,\"P1OximeterLink\":0,\"P2Minute\":19,\"P4ALinkStatus\":1,\"P1OximeterSaturation\":0,\"P4BatteryPower\":254,\"P3ALinkStatus\":1,\"P3BBreathRate\":0,\"P1BBreathRate\":0,\"P2Date\":26,\"P3Year\":19,\"P4ATurnOverTimes\":0,\"P5OximeterSaturation\":0,\"P1Minute\":19,\"P1BHeartRate\":0,\"P5AHasAnybody\":0,\"P2BBreathRate\":0,\"P2OximeterPerfusion\":0,\"P1OximeterDataType\":0,\"P4Date\":26,\"P2AActivityEnergy\":0,\"P5BTurnOverTimes\":0,\"P3Month\":7,\"P3OximeterPerfusion\":0,\"P3AHasAnybody\":0,\"P2Second\":35,\"P4OximeterDataType\":0,\"P1Hour\":10,\"P4Minute\":19,\"P5Minute\":19,\"P2OximeterSaturation\":0,\"P3SDStatus\":0,\"P1ALinkStatus\":1,\"P1Month\":7,\"P2SDStatus\":0,\"P4NetworkStatus\":118,\"P4BHeartRate\":0,\"P5BHasAnybody\":0,\"P1BActivityEnergy\":0,\"P3BHasAnybody\":0,\"P1AHeartRate\":0,\"P2OximeterDataType\":0,\"P2BatteryPower\":254,\"P5BatteryPower\":254,\"P3BHeartRate\":0,\"P4ABreathRate\":0,\"P2AHeartRate\":0,\"P5SDStatus\":0,\"P5Second\":38,\"_bindUserId\":1034752,\"P1OximeterPower\":0,\"P4Year\":19,\"P2ABreathRate\":0,\"P3OximeterDataType\":0,\"P4SDStatus\":0,\"P4AHasAnybody\":0,\"deviceId\":110683,\"P5OximeterLink\":0,\"P3OximeterSaturation\":0,\"P4AActivityEnergy\":0,\"P4BHasAnybody\":0,\"P5Month\":7,\"P3BLinkStatus\":0,\"P5BHeartRate\":0,\"P4BTurnOverTimes\":0,\"TotalPacketNum\":5,\"P3ABreathRate\":0,\"P5AActivityEnergy\":0,\"P1BTurnOverTimes\":0,\"P1BatteryPower\":254,\"P5Hour\":10,\"P4BActivityEnergy\":0,\"P2BActivityEnergy\":0,\"P5OximeterPulse\":0,\"P1Second\":34,\"P4Hour\":10,\"P1BLinkStatus\":0,\"P3NetworkStatus\":118,\"P5NetworkStatus\":118,\"P5Date\":26,\"P1BHasAnybody\":0,\"P1AActivityEnergy\":0,\"P5OximeterDataType\":0,\"P3BatteryPower\":254,\"P2ATurnOverTimes\":0,\"P2BTurnOverTimes\":0,\"P2Hour\":10,\"P3BTurnOverTimes\":0,\"P3OximeterPower\":0,\"P5BBreathRate\":0,\"P3Date\":26,\"P2AHasAnybody\":0,\"P2Month\":7,\"P2OximeterLink\":0,\"P3Second\":36,\"P5ATurnOverTimes\":0,\"P4OximeterPerfusion\":0,\"productId\":139,\"P2BLinkStatus\":0,\"P4OximeterPulse\":0,\"P2NetworkStatus\":118,\"P5BLinkStatus\":0,\"P4OximeterLink\":0,\"P1OximeterPulse\":0,\"P4BLinkStatus\":0}}}";
//        String message = "ALP_RUN_DATA_TOPIC消息测试,测试128个字节，测试128个字节，测试128个字节，测试128个字节，测试128个字节";
//        String message = "{\"session_id\":\"405EE11002A4\",\"from_cluster\":\"10.104.228.51:25861:1562588851383\",\"general_message\":{\"dataMode\":1,\"packet\":null,\"packetStart\":\"5A\",\"source\":0,\"developerID\":\"1\",\"msgId\":null,\"macAddress\":\"405EE11002A4\",\"command\":\"0105\",\"sendTo\":\"6_3_1\",\"deviceType\":6,\"deviceSubType\":3,\"deviceBrand\":null,\"dataVersion\":1,\"packetSequence\":236282,\"direction\":0,\"bussinessType\":1,\"data\":{\"P2BHeartRate\":0,\"dataTimeStamp\":1564078777526,\"P4BBreathRate\":0,\"P2ALinkStatus\":1,\"P1NetworkStatus\":118,\"P1AHasAnybody\":0,\"P3ATurnOverTimes\":0,\"P3BActivityEnergy\":0,\"P5ABreathRate\":0,\"P5AHeartRate\":0,\"P4Month\":7,\"P2BHasAnybody\":0,\"P5OximeterPower\":0,\"P3OximeterPulse\":0,\"P4AHeartRate\":0,\"P1ABreathRate\":0,\"P4Second\":37,\"P5BActivityEnergy\":0,\"P2OximeterPower\":0,\"P1Year\":19,\"P3AActivityEnergy\":0,\"P2OximeterPulse\":0,\"P1OximeterPerfusion\":0,\"P5ALinkStatus\":1,\"P4OximeterSaturation\":0,\"P5OximeterPerfusion\":0,\"P5Year\":19,\"P3Minute\":19,\"P3AHeartRate\":0,\"P1SDStatus\":0,\"P2Year\":19,\"P1Date\":26,\"P3Hour\":10,\"P3OximeterLink\":0,\"P4OximeterPower\":0,\"P1ATurnOverTimes\":0,\"P1OximeterLink\":0,\"P2Minute\":19,\"P4ALinkStatus\":1,\"P1OximeterSaturation\":0,\"P4BatteryPower\":254,\"P3ALinkStatus\":1,\"P3BBreathRate\":0,\"P1BBreathRate\":0,\"P2Date\":26,\"P3Year\":19,\"P4ATurnOverTimes\":0,\"P5OximeterSaturation\":0,\"P1Minute\":19,\"P1BHeartRate\":0,\"P5AHasAnybody\":0,\"P2BBreathRate\":0,\"P2OximeterPerfusion\":0,\"P1OximeterDataType\":0,\"P4Date\":26,\"P2AActivityEnergy\":0,\"P5BTurnOverTimes\":0,\"P3Month\":7,\"P3OximeterPerfusion\":0,\"P3AHasAnybody\":0,\"P2Second\":35,\"P4OximeterDataType\":0,\"P1Hour\":10,\"P4Minute\":19,\"P5Minute\":19,\"P2OximeterSaturation\":0,\"P3SDStatus\":0,\"P1ALinkStatus\":1,\"P1Month\":7,\"P2SDStatus\":0,\"P4NetworkStatus\":118,\"P4BHeartRate\":0,\"P5BHasAnybody\":0,\"P1BActivityEnergy\":0,\"P3BHasAnybody\":0,\"P1AHeartRate\":0,\"P2OximeterDataType\":0,\"P2BatteryPower\":254,\"P5BatteryPower\":254,\"P3BHeartRate\":0,\"P4ABreathRate\":0,\"P2AHeartRate\":0,\"P5SDStatus\":0,\"P5Second\":38,\"_bindUserId\":1034752,\"P1OximeterPower\":0,\"P4Year\":19,\"P2ABreathRate\":0,\"P3OximeterDataType\":0,\"P4SDStatus\":0}}}";
//        String message = "{\"session_id\":\"ACCF23DF06AF\",\"from_cluster\":\"10.8.34.7:25861:1565346282472\",\"general_message\":{\"dataMode\":0,\"packet\":null,\"packetStart\":\"5A\",\"source\":0,\"developerID\":\"1\",\"msgId\":null,\"macAddress\":\"ACCF23DF06AF\",\"command\":\"0105\",\"sendTo\":\"11_1_1\",\"deviceType\":11,\"deviceSubType\":1,\"deviceBrand\":null,\"dataVersion\":4,\"packetSequence\":262832,\"direction\":0,\"bussinessType\":1,\"data\":{\"dataTimeStamp\":1565560361016,\"addtimeh\":0,\"orderBrightness\":0,\"Localmin\":52,\"addtimem\":0,\"out2\":0,\"remainPresetRuntimeH\":0,\"presetRuntimeM\":0,\"addtimemin\":0,\"out1\":0,\"Localhour\":13,\"mode\":0,\"presetOpenH\":0,\"timeCloseM\":0,\"workstate\":0,\"brightness\":71,\"_bindUserId\":88891306,\"remainingTimeM\":0,\"presetRuntimeH\":0,\"Localyear\":19,\"workmode\":0,\"resver2\":0,\"resver1\":0,\"remainingTimeH\":0,\"deviceId\":23592892,\"addcntm\":0,\"warring2\":0,\"Localday\":12,\"addcntl\":0,\"orderGreen\":0,\"warring1\":0,\"addcnth\":0,\"green\":169,\"Localmonth\":8,\"orderBlue\":0,\"remainPresetRuntimeM\":0,\"reserve1\":0,\"productId\":1755,\"runstate\":0,\"red\":252,\"blue\":108,\"remainPresetOpenM\":0,\"presetOpenM\":0,\"addrimel\":0,\"orderMist\":0,\"Localsec\":37,\"remainPresetOpenH\":0,\"timeCloseH\":0,\"orderRed\":0,\"mist\":0}}}";
//        String message = "{\"session_id\":\"4CCCCCC18552\",\"from_cluster\":\"10.7.0.53:25861:1565948149182\",\"general_message\":{\"dataMode\":1,\"packet\":null,\"packetStart\":\"5A\",\"source\":0,\"developerID\":\"1\",\"msgId\":null,\"macAddress\":\"4CCCCCC18552\",\"command\":\"0105\",\"sendTo\":\"6_3_1\",\"deviceType\":6,\"deviceSubType\":3,\"deviceBrand\":null,\"dataVersion\":1,\"packetSequence\":4228,\"direction\":0,\"bussinessType\":1,\"data\":{\"P2BHeartRate\":0,\"dataTimeStamp\":1566157766834,\"P4BBreathRate\":0,\"P2ALinkStatus\":1,\"P1NetworkStatus\":\"114\",\"P1AHasAnybody\":0,\"P3ATurnOverTimes\":0,\"P3BActivityEnergy\":0,\"P5ABreathRate\":0,\"P5AHeartRate\":0,\"P4Month\":2,\"P2BHasAnybody\":0,\"P5OximeterPower\":0,\"P3OximeterPulse\":0,\"P4AHeartRate\":0,\"P1ABreathRate\":0,\"P4Second\":49,\"P5BActivityEnergy\":0,\"P2OximeterPower\":0,\"P1Year\":19,\"P3AActivityEnergy\":0,\"P2OximeterPulse\":0,\"P1OximeterPerfusion\":0,\"P5ALinkStatus\":1,\"P4OximeterSaturation\":0,\"P5OximeterPerfusion\":0,\"P5Year\":19,\"P3Minute\":9,\"P3AHeartRate\":0,\"P1SDStatus\":0,\"P2Year\":19,\"P1Date\":22,\"P3Hour\":0,\"P3OximeterLink\":0,\"P4OximeterPower\":0,\"P1ATurnOverTimes\":0,\"P1OximeterLink\":0,\"P2Minute\":9,\"P4ALinkStatus\":1,\"P1OximeterSaturation\":0,\"P4BatteryPower\":100,\"P3ALinkStatus\":1,\"P3BBreathRate\":0,\"P1BBreathRate\":0,\"P2Date\":22,\"P3Year\":19,\"P4ATurnOverTimes\":0,\"P5OximeterSaturation\":0,\"P1Minute\":9,\"P1BHeartRate\":0,\"P5AHasAnybody\":0,\"P2BBreathRate\":0,\"P2OximeterPerfusion\":0,\"P1OximeterDataType\":0,\"P4Date\":22,\"P2AActivityEnergy\":0,\"P5BTurnOverTimes\":0,\"P3Month\":2,\"P3OximeterPerfusion\":0,\"P3AHasAnybody\":0,\"P2Second\":47,\"P4OximeterDataType\":0,\"P1Hour\":0,\"P4Minute\":9,\"P5Minute\":9,\"P2OximeterSaturation\":0,\"P3SDStatus\":0,\"P1ALinkStatus\":1,\"P1Month\":2,\"P2SDStatus\":0,\"P4NetworkStatus\":\"114\",\"P4BHeartRate\":0,\"P5BHasAnybody\":0,\"P1BActivityEnergy\":0,\"P3BHasAnybody\":0,\"P1AHeartRate\":0,\"P2OximeterDataType\":0,\"P2BatteryPower\":100,\"P5BatteryPower\":100,\"P3BHeartRate\":0,\"P4ABreathRate\":0,\"P2AHeartRate\":0,\"P5SDStatus\":0,\"P5Second\":50,\"_bindUserId\":0,\"P1OximeterPower\":0,\"P4Year\":19,\"P2ABreathRate\":0,\"P3OximeterDataType\":0,\"P4SDStatus\":0,\"P4AHasAnybody\":0,\"deviceId\":12561358,\"P5OximeterLink\":0,\"P3OximeterSaturation\":0,\"P4AActivityEnergy\":0,\"P4BHasAnybody\":0,\"P5Month\":2,\"P3BLinkStatus\":0,\"P5BHeartRate\":0,\"P4BTurnOverTimes\":0,\"TotalPacketNum\":5,\"P3ABreathRate\":0,\"P5AActivityEnergy\":0,\"P1BTurnOverTimes\":0,\"P1BatteryPower\":100,\"P5Hour\":0,\"P4BActivityEnergy\":0,\"P2BActivityEnergy\":0,\"P5OximeterPulse\":0,\"P1Second\":46,\"P4Hour\":0,\"P1BLinkStatus\":0,\"P3NetworkStatus\":\"114\",\"P5NetworkStatus\":\"114\",\"P5Date\":22,\"P1BHasAnybody\":0,\"P1AActivityEnergy\":0,\"P5OximeterDataType\":0,\"P3BatteryPower\":100,\"P2ATurnOverTimes\":0,\"P2BTurnOverTimes\":0,\"P2Hour\":0,\"P3BTurnOverTimes\":0,\"P3OximeterPower\":0,\"P5BBreathRate\":0,\"P3Date\":22,\"P2AHasAnybody\":0,\"P2Month\":2,\"P2OximeterLink\":0,\"P3Second\":48,\"P5ATurnOverTimes\":0,\"P4OximeterPerfusion\":0,\"productId\":139,\"P2BLinkStatus\":0,\"P4OximeterPulse\":0,\"P2NetworkStatus\":\"114\",\"P5BLinkStatus\":0,\"P4OximeterLink\":0,\"P1OximeterPulse\":0,\"P4BLinkStatus\":0}}}";
        String message = "{\"session_id\":\"405EE1100049\",\"from_cluster\":\"10.104.228.51:25861:1562588851383\",\"general_message\":{\"dataMode\":1,\"packet\":null,\"packetStart\":\"5A\",\"source\":0,\"developerID\":\"1\",\"msgId\":null,\"macAddress\":\"405EE1100049\",\"command\":\"0105\",\"sendTo\":\"6_3_1\",\"deviceType\":6,\"deviceSubType\":3,\"deviceBrand\":null,\"dataVersion\":1,\"packetSequence\":236282,\"direction\":0,\"bussinessType\":1,\"data\":{\"P2BHeartRate\":0,\"dataTimeStamp\":1564078777526,\"P4BBreathRate\":0,\"P2ALinkStatus\":1,\"P1NetworkStatus\":118,\"P1AHasAnybody\":0,\"P3ATurnOverTimes\":0,\"P3BActivityEnergy\":0,\"P5ABreathRate\":0,\"P5AHeartRate\":0,\"P4Month\":7,\"P2BHasAnybody\":0,\"P5OximeterPower\":0,\"P3OximeterPulse\":0,\"P4AHeartRate\":0,\"P1ABreathRate\":0,\"P4Second\":37,\"P5BActivityEnergy\":0,\"P2OximeterPower\":0,\"P1Year\":19,\"P3AActivityEnergy\":0,\"P2OximeterPulse\":0,\"P1OximeterPerfusion\":0,\"P5ALinkStatus\":1,\"P4OximeterSaturation\":0,\"P5OximeterPerfusion\":0,\"P5Year\":19,\"P3Minute\":19,\"P3AHeartRate\":0,\"P1SDStatus\":0,\"P2Year\":19,\"P1Date\":26,\"P3Hour\":10,\"P3OximeterLink\":0,\"P4OximeterPower\":0,\"P1ATurnOverTimes\":0,\"P1OximeterLink\":0,\"P2Minute\":19,\"P4ALinkStatus\":1,\"P1OximeterSaturation\":0,\"P4BatteryPower\":254,\"P3ALinkStatus\":1,\"P3BBreathRate\":0,\"P1BBreathRate\":0,\"P2Date\":26,\"P3Year\":19,\"P4ATurnOverTimes\":0,\"P5OximeterSaturation\":0,\"P1Minute\":19,\"P1BHeartRate\":0,\"P5AHasAnybody\":0,\"P2BBreathRate\":0,\"P2OximeterPerfusion\":0,\"P1OximeterDataType\":0,\"P4Date\":26,\"P2AActivityEnergy\":0,\"P5BTurnOverTimes\":0,\"P3Month\":7,\"P3OximeterPerfusion\":0,\"P3AHasAnybody\":0,\"P2Second\":35,\"P4OximeterDataType\":0,\"P1Hour\":10,\"P4Minute\":19,\"P5Minute\":19,\"P2OximeterSaturation\":0,\"P3SDStatus\":0,\"P1ALinkStatus\":1,\"P1Month\":7,\"P2SDStatus\":0,\"P4NetworkStatus\":118,\"P4BHeartRate\":0,\"P5BHasAnybody\":0,\"P1BActivityEnergy\":0,\"P3BHasAnybody\":0,\"P1AHeartRate\":0,\"P2OximeterDataType\":0,\"P2BatteryPower\":254,\"P5BatteryPower\":254,\"P3BHeartRate\":0,\"P4ABreathRate\":0,\"P2AHeartRate\":0,\"P5SDStatus\":0,\"P5Second\":38,\"_bindUserId\":1034752,\"P1OximeterPower\":0,\"P4Year\":19,\"P2ABreathRate\":0,\"P3OximeterDataType\":0,\"P4SDStatus\":0,\"P4AHasAnybody\":0,\"deviceId\":110683,\"P5OximeterLink\":0,\"P3OximeterSaturation\":0,\"P4AActivityEnergy\":0,\"P4BHasAnybody\":0,\"P5Month\":7,\"P3BLinkStatus\":0,\"P5BHeartRate\":0,\"P4BTurnOverTimes\":0,\"TotalPacketNum\":5,\"P3ABreathRate\":0,\"P5AActivityEnergy\":0,\"P1BTurnOverTimes\":0,\"P1BatteryPower\":254,\"P5Hour\":10,\"P4BActivityEnergy\":0,\"P2BActivityEnergy\":0,\"P5OximeterPulse\":0,\"P1Second\":34,\"P4Hour\":10,\"P1BLinkStatus\":0,\"P3NetworkStatus\":118,\"P5NetworkStatus\":118,\"P5Date\":26,\"P1BHasAnybody\":0,\"P1AActivityEnergy\":0,\"P5OximeterDataType\":0,\"P3BatteryPower\":254,\"P2ATurnOverTimes\":0,\"P2BTurnOverTimes\":0,\"P2Hour\":10,\"P3BTurnOverTimes\":0,\"P3OximeterPower\":0,\"P5BBreathRate\":0,\"P3Date\":26,\"P2AHasAnybody\":0,\"P2Month\":7,\"P2OximeterLink\":0,\"P3Second\":36,\"P5ATurnOverTimes\":0,\"P4OximeterPerfusion\":0,\"productId\":139,\"P2BLinkStatus\":0,\"P4OximeterPulse\":0,\"P2NetworkStatus\":118,\"P5BLinkStatus\":0,\"P4OximeterLink\":0,\"P1OximeterPulse\":0,\"P4BLinkStatus\":0}}}";
        String topic = "ALP_RUN_DATA_TOPIC";
        String tag = "11_1_1_1755";

        DefaultMQProducer producer = new DefaultMQProducer(groupName);
        producer.setNamesrvAddr(namesrvAddr);
        producer.setInstanceName(instanceName);
        producer.setMaxMessageSize(maxMessageSize);
        producer.setSendMsgTimeout(sendMsgTimeout);
        producer.start();

        long startTime = System.currentTimeMillis();
        long count = 0L;
        while (true) {
            Message msg = new Message(topic, tag, message.getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.sendOneway(msg);
//            SendResult send = producer.send(msg);
            ++count;
            if (count % 10000 == 0) {
                long consumeTime = System.currentTimeMillis() - startTime;
                System.out.println("消息数量：" + count / 10000 + "万条，单条消息长度：" +
                        message.getBytes().length + "字节，耗时：" + consumeTime + "毫秒，tps：" +
                        (count / consumeTime * 1000));
            }
        }

    }

}
