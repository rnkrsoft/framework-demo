package com.rnkrsoft.framework.demo.messagequeue;

import com.rnkrsoft.framework.messagequeue.annotation.Message;
import lombok.Data;

@Data
@Message(routingKey = "test.routingkey")
public class Bean1 {
    String name;
    int age;
}