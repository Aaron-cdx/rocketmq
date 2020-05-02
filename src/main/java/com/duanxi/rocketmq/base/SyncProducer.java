package com.duanxi.rocketmq.base;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author caoduanxi
 * @Date 2020/5/1 23:14
 * 发送同步消息
 */
public class SyncProducer {
    public static void main(String[] args) throws Exception {
        // 实例化消息生产者,需要有分组
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        // 设置地址
        producer.setNamesrvAddr("192.168.230.100:9876;192.168.230.101:9876");
        // 启动producer
        producer.start();
        // 生产消息
        for (int i = 0; i < 10; i++) {
            // 创建消息体
            Message msg = new Message("TopicTest","TagA",
                    ("hello world " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            // 发送消息会获取一个消息发送的结果，因为是同步的，所以必须会返回一个结果
            SendResult sendResult = producer.send(msg);
            System.out.println("发送消息的结果："+sendResult);
        }
        // 关闭producer
        producer.shutdown();

    }
}
