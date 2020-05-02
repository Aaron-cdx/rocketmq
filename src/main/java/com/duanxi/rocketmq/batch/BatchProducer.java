package com.duanxi.rocketmq.batch;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caoduanxi
 * @Date 2020/5/2 11:18
 * 批量发送消息
 */
public class BatchProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        producer.setNamesrvAddr("192.168.230.100:9876;192.168.230.101:9876");
        producer.start();

        List<Message> msgs = new ArrayList<>();
        Message msg1 = new Message("batchTopic","tag1","hello caoduanxi 1".getBytes());
        Message msg2 = new Message("batchTopic","tag1","hello caoduanxi 2".getBytes());
        Message msg3 = new Message("batchTopic","tag1","hello caoduanxi 3".getBytes());

        msgs.add(msg1);
        msgs.add(msg2);
        msgs.add(msg3);

        SendResult send = producer.send(msgs);
        System.out.println("发送消息："+send);

        producer.shutdown();
    }
}
