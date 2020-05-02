package com.duanxi.rocketmq.delay;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * @author caoduanxi
 * @Date 2020/5/2 10:47
 * 发送延时消息
 * 注意rocketMq最多支持2个小时的延时，有18个等级
 */
public class DelayProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        producer.setNamesrvAddr("192.168.230.100:9876;192.168.230.101:9876");
        producer.start();

        for (int i = 0; i < 10; i++) {
            Message msg = new Message("delayTopic","TagA",
                    ("hello caoduanxi "+i).getBytes());
            msg.setDelayTimeLevel(2);
            SendResult send = producer.send(msg);
        }
        producer.shutdown();
    }
}
