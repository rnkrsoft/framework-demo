package com.rnkrsoft.framework.demo.messagequeue;

import com.rnkrsoft.framework.messagequeue.protocol.Message;
import com.rnkrsoft.framework.messagequeue.protocol.MessageQueueConsumer;
import com.rnkrsoft.framework.messagequeue.protocol.MessageQueueProducer;
import com.rnkrsoft.framework.test.SpringTest;
import lombok.Data;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by rnkrsoft.com on 2018/12/9.
 */
@ContextConfiguration(locations = "classpath*:testContext-mq-product.xml")
public class RabbitMqProducerTest extends SpringTest{
    @Data
    static class Bean1 {
        String name;
        int age;
    }

    @Autowired
    MessageQueueProducer messageQueueProducer;

    /**
     * 测试生产消息，基于Rabbit MQ
     */
    @Test
    public void test2(){
        Bean1 bean1 = new Bean1();

        for (int i = 0; i < 1000; i++) {
            bean1.setName("xxxxx");
            bean1.setAge(i);
            Message message = new Message(bean1);
            message.setRoutingKey("test.routingkey");
            messageQueueProducer.produce(message);
        }
    }
}
