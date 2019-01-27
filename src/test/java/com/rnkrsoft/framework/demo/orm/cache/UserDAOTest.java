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