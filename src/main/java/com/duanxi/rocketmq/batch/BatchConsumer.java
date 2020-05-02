package com.duanxi.rocketmq.batch;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * @author caoduanxi
 * @Date 2020/5/2 11:22
 * 批量消费
 */
public class BatchConsumer {
    public static void main(String[] args) throws Exception {
        // 构造一个消费者
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group1");
        // 设置存储数据的broker地址
        consumer.setNamesrvAddr("192.168.230.100:9876;192.168.230.101:9876");
        // 订阅topic与标签tag
        consumer.subscribe("batchTopic", "*");
        // 注册回调函数，即监听器，监听是否有此topic的tagA的消息，有的话就消费
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.println(Thread.currentThread().getName() + "消费：" + new String(msg.getBody()));
                }
                // 需要返回消费成功
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        // 启动消费者
        consumer.start();
        System.out.println("消费者成功启动");
    }
}
