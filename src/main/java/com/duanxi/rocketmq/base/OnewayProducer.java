package com.duanxi.rocketmq.base;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author caoduanxi
 * @Date 2020/5/1 23:34
 * 单向发送消息:主要用于不用关心发送结果的场景，无论成功失败，只管发送！
 */
public class OnewayProducer {
    public static void main(String[] args) throws Exception {
        // 实例化消息生产者
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        // 设置地址
        producer.setNamesrvAddr("192.168.230.100:9876;192.168.230.101:9876");
        // 启动producer
        producer.start();
        // 生产消息
        for (int i = 0; i < 10; i++) {
            // 创建消息体
            Message msg = new Message("TopicTest", "TagC",
                    ("hello world " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            // 发送单向消息,没有任何返回结果
            producer.sendOneway(msg);
        }
        // 关闭producer
        producer.shutdown();

    }
}
