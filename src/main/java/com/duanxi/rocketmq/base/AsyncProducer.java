package com.duanxi.rocketmq.base;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author caoduanxi
 * @Date 2020/5/1 23:29
 * 异步发送消息
 */
public class AsyncProducer {
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
            Message msg = new Message("TopicTest", "TagB",
                    ("hello world " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            // 异步发送消息,异步发送消息的话会获取到一个回调函数
            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("异步消息发送成功！发送结果：" + sendResult);
                }

                @Override
                public void onException(Throwable e) {
                    System.out.println("消息发送失败！exception: "+e);
                    e.printStackTrace();
                }
            });
        }
        // 关闭producer
        producer.shutdown();

    }
}
