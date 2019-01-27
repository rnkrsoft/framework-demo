package com.rnkrsoft.framework.demo.messagequeue;

import com.rnkrsoft.framework.messagequeue.protocol.MessageQueueConsumer;
import com.rnkrsoft.framework.test.SpringTest;
import lombok.Data;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by rnkrsoft.com on 2018/12/9.
 */
@ContextConfiguration(locations = "classpath*:testContext-mq-consumer.xml")
public class RabbitMqConsumerTest extends SpringTest{
    @Autowired
    MessageQueueConsumer messageQueueConsumer;
    @Test
    public void test1() throws InterruptedException {
        Thread.sleep(100000);
    }
}
