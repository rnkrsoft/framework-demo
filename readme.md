# 快速入门Framework

## MySQL ORM

通过对JPA或者自定义注解在Mybatis基础上拓展

```java
package com.rnkrsoft.framework.demo.orm.mysql.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.rnkrsoft.framework.orm.PrimaryKey;
import com.rnkrsoft.framework.orm.PrimaryKeyStrategy;

import com.rnkrsoft.framework.orm.jdbc.*;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 *  rnkrsoft.com 框架自动生成!
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "tb_agent_info")
@Comment("代理信息表")
public class TbAgentInfoEntity implements Serializable {
    @Comment("主键ID")
    @PrimaryKey(strategy = PrimaryKeyStrategy.UUID)
    @StringColumn(name = "ID", nullable = false, type = StringType.VARCHAR)
    String id;

    @Comment("代理名字")
    @StringColumn(name = "AGENT_NAME", nullable = false, type = StringType.VARCHAR)
    String agentName;

    @Comment("代理电话")
    @StringColumn(name = "PHONE", nullable = false, type = StringType.VARCHAR)
    String phone;

    @Comment("代理住址")
    @StringColumn(name = "ADDRESS", nullable = true, type = StringType.VARCHAR)
    String address;

    @Comment("省份")
    @StringColumn(name = "PROVINCE", nullable = true, type = StringType.VARCHAR)
    String province;

    @Comment("城市")
    @StringColumn(name = "CITY", nullable = true, type = StringType.VARCHAR)
    String city;

    @Comment("区/县")
    @StringColumn(name = "REGION", nullable = true, type = StringType.VARCHAR)
    String region;

    @Comment("创建人ID")
    @StringColumn(name = "ADD_USER_ID", nullable = false, type = StringType.VARCHAR)
    String addUserId;

    @Comment("添加时间")
    @DateColumn(name = "ADD_TIME", nullable = false, type = DateType.TIMESTAMP)
    Timestamp addTime;

    @Comment("修改人ID")
    @StringColumn(name = "UPDATE_USER_ID", nullable = false, type = StringType.VARCHAR)
    String updateUserId;

    @Comment("修改时间")
    @DateColumn(name = "UPDATE_TIME", nullable = false, type = DateType.TIMESTAMP)
    Timestamp updateTime;

}

```

数据访问对象

```java
package com.rnkrsoft.framework.demo.orm.mysql.dao;

import com.rnkrsoft.framework.demo.orm.mysql.entity.TbAgentInfoEntity;
import com.rnkrsoft.framework.orm.jdbc.JdbcMapper;

/**
 * rnkrsoft.com 框架自动生成!
 */
public interface TbAgentInfoDAO extends JdbcMapper<TbAgentInfoEntity, String> {

}
```

、

调用代码

```java
package com.rnkrsoft.framework.demo.orm.mysql;

import com.rnkrsoft.framework.demo.orm.mysql.dao.TbAgentInfoDAO;
import com.rnkrsoft.framework.demo.orm.mysql.entity.TbAgentInfoEntity;
import com.rnkrsoft.framework.test.CreateTable;
import com.rnkrsoft.framework.test.DataSource;
import com.rnkrsoft.framework.test.DataSourceTest;
import com.rnkrsoft.framework.test.DataSourceType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by rnkrsoft.com on 2018/4/30.
 */
@CreateTable(entities = TbAgentInfoEntity.class)
@ContextConfiguration("classpath*:testContext-orm.xml")
@DataSource(DataSourceType.H2)
public class TestDao extends DataSourceTest {
    @Autowired
    TbAgentInfoDAO tbAgentInfoDAO;
    @Test
    public void test1(){
        //MySQL 支持如下特性
        //1.在实体中指定物理主键值
        //2.使用UUID自动生成，在实体插入后读取主键值
        //3.使用MySQL表的自增整数主键，在实体插入后读取主键值
        //4.使用SequenceService提供的自增整数主键值，在实体插入后读取主键值
        //5.使用表达式提供主键值，只适用字符串类型的主键值，支持表达式为
        //      a.固定字符串
        //      b.${yyyyMMddHHmmssSSS} 日期变量表达式
        //      c.${SEQ:长度} 使用SequenceService提供自增整数序号，并进行左边填充指定长度的
        //      d.${RANDOM:长度} 使用随机值，并进行指定长度的填充
        String id = null;
        {
            TbAgentInfoEntity entity = new TbAgentInfoEntity();
            entity.setAgentName("xxx");
            entity.setAddress("xxx");
            entity.setPhone("xxx");
            entity.setAddUserId("xxx");
            entity.setUpdateUserId("xxx");
            tbAgentInfoDAO.insertSelective(entity);
            id = entity.getId();
        }
        {
            TbAgentInfoEntity entity = new TbAgentInfoEntity();
            entity.setAgentName("xxx");
            entity.setAddress("xxx");
            entity.setPhone("xxx");
            entity.setAddUserId("xxx");
            entity.setUpdateUserId("xxx");
            tbAgentInfoDAO.insert(entity);
        }
        tbAgentInfoDAO.selectAll();

        TbAgentInfoEntity entity1 = tbAgentInfoDAO.selectByPrimaryKey(id);
        System.out.println(entity1);

        TbAgentInfoEntity entity2 = new TbAgentInfoEntity();
        entity2.setId(id);
        entity2.setPhone("12345678");
        tbAgentInfoDAO.updateByPrimaryKeySelective(entity2);

        tbAgentInfoDAO.deleteByPrimaryKey(id);
    }
}

```



## MongoDB ORM

```java
package com.rnkrsoft.framework.demo.orm.mongo.entity;

import com.rnkrsoft.framework.orm.LogicMode;
import com.rnkrsoft.framework.orm.PrimaryKey;
import com.rnkrsoft.framework.orm.PrimaryKeyStrategy;
import com.rnkrsoft.framework.orm.ValueMode;
import com.rnkrsoft.framework.orm.mongo.MongoColumn;
import com.rnkrsoft.framework.orm.mongo.MongoTable;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by rnkrsoft.com on 2018/6/2.
 */
@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@MongoTable(name = "OPERATE_LOG", schema = "xxxx")
public class Example1Entity implements Serializable {
    @PrimaryKey(strategy = PrimaryKeyStrategy.EXPRESSION, feature = "ABCD_${yyyyMMddHHmmssSSS}_${SEQ:5}_${SEQ:8}${SEQ:8}_${RANDOM:8}_EFGH")
    @MongoColumn(name = "_id", valueMode = ValueMode.EQUAL, nullable = false)
    String id;

    @MongoColumn(name = "NAME", logicMode = LogicMode.OR, valueMode = ValueMode.EQUAL)
    String name;

    @MongoColumn(name = "AGE", logicMode = LogicMode.AND, valueMode = ValueMode.EQUAL)
    Integer age;

    @MongoColumn(name = "DATA", logicMode = LogicMode.OR, valueMode = ValueMode.LTE)
    String data;

    @MongoColumn(name = "CREATE_DATE", logicMode = LogicMode.OR, valueMode = ValueMode.LTE)
    Date createDate;
}
```



```java
package com.rnkrsoft.framework.demo.orm.mongo.dao;

import com.rnkrsoft.framework.demo.orm.mongo.entity.Example1Entity;
import com.rnkrsoft.framework.orm.mongo.MongoMapper;


/**
 * Created by rnkrsoft.com on 2018/6/2.
 */
public interface Example1DAO extends MongoMapper<Example1Entity> {

}

```



```java
package com.rnkrsoft.framework.demo.orm.mongo;

import com.rnkrsoft.framework.demo.orm.mongo.dao.Example1DAO;
import com.rnkrsoft.framework.demo.orm.mongo.entity.Example1Entity;
import com.rnkrsoft.framework.orm.Pagination;
import com.rnkrsoft.framework.test.SpringTest;
import com.rnkrsoft.utils.DateUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

/**
 * Created by rnkrsoft.com on 2019/1/27.
 */
@ContextConfiguration(locations = "classpath*:testContext-mongodb.xml")
public class MongoTest extends SpringTest{
    @Autowired
    Example1DAO example1DAO;

    @Test
    public void testInsert(){
        example1DAO.insert(Example1Entity.builder().name("test").age(2).data(DateUtils.getCurrFullTime()).id("1234567890").build());
        List<Example1Entity> list = example1DAO.select(Example1Entity.builder().name("test").build());
        System.out.println(list);
        Example1Entity example1Entity = new Example1Entity();
        example1Entity.setAge(20);
        Pagination<Example1Entity> pagination = new Pagination<Example1Entity>(20, 1, example1Entity);
        example1DAO.selectPage(pagination);
        for (Example1Entity entity :pagination.getRecords()){
            System.out.println(entity);
        }
    }
}

```



## Cache ORM

```java
package com.rnkrsoft.framework.demo.orm.cache.entity;

import lombok.ToString;

/**
 * Created by rnkrsoft.com on 2018/6/2.
 */
@ToString
public class User {
    String name;
    int age;
    Boolean sex;

    public User(String name, int age, Boolean sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
}

```



```java
package com.rnkrsoft.framework.demo.orm.cache.dao;

import com.rnkrsoft.framework.cache.client.CachedMap;
import com.rnkrsoft.framework.demo.orm.cache.entity.User;
import com.rnkrsoft.framework.orm.cache.*;

/**
 * Created by rnkrsoft.com on 2018/6/2.
 */
@Cache(expire = 6000, index = 1, prefix = "")
public interface UserDAO extends CacheInterface {
    @Set
    void set(String key, User user);

    @Set
    void init(String key, Integer val);

    @Get
    User getUser(String key);

    @Get
    Long getLong(String key);

    @GetSet(expire = 100)
    User getSet(String key, User user);

    @Expire
    void expire(String key, int second);

    @Presist
    void presist(String key);

    @Ttl
    Long ttl(String key);

    @Incr(increment = 2)
    Long incr(String key);

    @Decr(decrement = 3)
    Long decr(String key);

    @Keys
    java.util.Set<String> keys(String pattern);

    @Type
    Class type(String key);

    CachedMap get();
}

```



```java
package com.rnkrsoft.framework.demo.orm.cache;

import com.rnkrsoft.framework.cache.client.CachedMap;
import com.rnkrsoft.framework.demo.orm.cache.entity.User;
import com.rnkrsoft.framework.demo.orm.cache.dao.UserDAO;
import com.rnkrsoft.framework.test.SpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.UUID;

/**
 * Created by rnkrsoft.com on 2018/6/8.
 */
@ContextConfiguration(locations = "classpath*:testContext-cache.xml")
public class UserDAOTest extends SpringTest {
    @Autowired
    UserDAO userDAO;

    @Test
    public void testSet() throws Exception {
        //初始化键xxxxxxx的值为0
        userDAO.init("xxxxxxx", 0);
        //键xxxxxxx自增2
        userDAO.incr("xxxxxxx");
        //键xxxxxxx自减3
        userDAO.decr("xxxxxxx");
        //设置键xxxxxxx 60秒后超时
        userDAO.expire("xxxxxxx", 60);
        Thread.sleep(59 * 1000);
        //59秒时读取键xxxxxxx的值
        System.out.println(userDAO.getLong("xxxxxxx"));
        Thread.sleep(2 * 1000);
        //61秒时读取键xxxxxxx的值
        System.out.println(userDAO.getLong("xxxxxxx"));

        //获取可缓存的Map对象
        CachedMap cachedMap = userDAO.get();
        //读取键xxxxxxx的值
        System.out.println(cachedMap.get("xxxxxxx"));
        //先读取yyyyyy对应的值，同时设置为新建对象
        User user = userDAO.getSet("yyyyyy", new User(UUID.randomUUID().toString(), 1000, false));
        //打印设置前的对象
        System.out.println(user);
        //打印设置后的对象
        System.out.println(userDAO.getUser("yyyyyy"));

        //读取对象类型
        System.out.println(userDAO.type("yyyyyy"));
        //设置2秒后超时
        userDAO.expire("yyyyyy", 2);
        //获取yyyyyy的超时时间
        System.out.println(userDAO.ttl("yyyyyy"));
        //移除yyyyyy的超时时间
        userDAO.presist("yyyyyy");
        Thread.sleep(3000);
        //休眠3秒后，在读取
        System.out.println(userDAO.ttl("yyyyyy"));
        System.out.println("----------" + userDAO.getUser("yyyyyy"));
        Thread.sleep(3000);
        System.out.println(userDAO.ttl("yyyyyy"));
        System.out.println("----------" + userDAO.getUser("yyyyyy"));
        System.out.println(userDAO.keys("*"));
        String key = UUID.randomUUID().toString();
        System.out.println(userDAO.getSet(key, new User(UUID.randomUUID().toString(), 21, false)));
        System.out.println(userDAO.keys("*"));
        System.out.println(userDAO.getSet(key, new User(UUID.randomUUID().toString(), 21, false)));
        System.out.println(userDAO.getSet(key, new User(UUID.randomUUID().toString(), 21, false)));
        System.out.println(userDAO.keys("*"));
    }
}
```



## 消息队列

```java
package com.rnkrsoft.framework.demo.messagequeue;

import com.rnkrsoft.framework.messagequeue.annotation.Message;
import lombok.Data;

@Data
@Message(routingKey = "test.routingkey")
public class Bean1 {
    String name;
    int age;
}
```



testContext-mq-product.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:jpa="http://www.springframework.org/schema/data/jpa"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:util="http://www.springframework.org/schema/util"
       xsi:schemaLocation="
		http://www.springframework.org/schema/beans
		http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context
		http://www.springframework.org/schema/context/spring-context.xsd
		http://www.springframework.org/schema/tx
		http://www.springframework.org/schema/tx/spring-tx.xsd
		http://www.springframework.org/schema/util
		http://www.springframework.org/schema/util/spring-util.xsd
		">

    <bean class="com.rnkrsoft.framework.messagequeue.rabbitmq.MessageQueueProducerRabbitMQ">
        <property name="uri" value="amqp://test:test@127.0.0.1:5672"/>
        <property name="exchangeName" value="framework.test"/>
    </bean>
</beans>

```



生产者

```java
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

```



消费者testContext-mq-consumer.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:jpa="http://www.springframework.org/schema/data/jpa"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:util="http://www.springframework.org/schema/util"
       xsi:schemaLocation="
		http://www.springframework.org/schema/beans
		http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context
		http://www.springframework.org/schema/context/spring-context.xsd
		http://www.springframework.org/schema/tx
		http://www.springframework.org/schema/tx/spring-tx.xsd
		http://www.springframework.org/schema/util
		http://www.springframework.org/schema/util/spring-util.xsd
		">
    <bean class="com.rnkrsoft.framework.messagequeue.rabbitmq.MessageQueueConsumerRabbitMQ">
        <property name="uri" value="amqp://test:test@127.0.0.1:5672"/>
        <property name="autoAck" value="false"/>
        <property name="consumeThreadNum" value="20"/>
        <property name="queueName" value="queue.test"/>
        <property name="messageQueueListeners">
            <array>
                <bean class="com.rnkrsoft.framework.demo.messagequeue.DemoMessageQueueListener"/>
            </array>
        </property>
    </bean>
</beans>

```



```java
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

```



## 序号服务

提供统一的连续递增序号生成服务

```java
package com.rnkrsoft.framework.sequence;

/**
 * Created by rnkrsoft.com on 2017/1/4.
 * 序号服务，用于提供连续自增序列
 */
public interface SequenceService {
    /**
     * 获取下一个序号值
     * @param schema 模式名
     * @param prefix 前缀
     * @param sequenceName 序列名称
     * @param feature 特征，如果固定的则是自增序列，如果是变化特征，则会从0开始自增
     * @return 序号值
     */
    long nextval(String schema, String prefix, String sequenceName, String feature);

    /**
     * 当前值
     * @param schema 模式名
     * @param prefix 前缀
     * @param sequenceName 序列名称
     * @param feature 特征，如果固定的则是自增序列，如果是变化特征，则会从0开始自增
     * @return 序号值
     */
    long curval(String schema, String prefix, String sequenceName, String feature);
}

```



该接口的实现类有

1.  InjvmSequenceService 基于虚机内部实现的序号自增，这个服务只用于开发测试，不能用于生产，可能导致内存溢出
2.  MyISAMSequenceService 基于MySQL MyISAM引擎的主键自增特性实现，可用于生产环境
3.  RedisSequenceService 基于Redis 自增特性实现，可用于生产环境
4.  可以自行实现服务，例如ZooKeeper实现等

获取服务实例使用
```java
SequenceService  ss = SequenceServiceFactory.instance("com.rnkrsoft.framework.sequence.redis.RedisSequenceService");
long l1 = sequenceService.nextval("", "", "user_id", DateUtils.getCurrDate());
```

