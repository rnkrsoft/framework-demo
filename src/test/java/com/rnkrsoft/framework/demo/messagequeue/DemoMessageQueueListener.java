package com.rnkrsoft.framework.demo.messagequeue;

import com.rnkrsoft.framework.messagequeue.annotation.SelectorType;
import com.rnkrsoft.framework.messagequeue.consumer.listener.MessageQueueSelector;
import com.rnkrsoft.framework.messagequeue.protocol.Message;
import com.rnkrsoft.framework.messagequeue.protocol.MessageQueueListener;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rnkrsoft.com on 2018/12/9.
 */
public class DemoMessageQueueListener implements MessageQueueListener {
    @Override
    public List<MessageQueueSelector> getSelectors() {
        return Arrays.asList(new MessageQueueSelector(SelectorType.necessarily, "test.routingkey"));
    }

    @Override
    public void execute(Message message) {
       try {
           System.out.println(message);
       }catch (Exception e){
           e.printStackTrace();
           e.printStackTrace();
       }
    }
}
