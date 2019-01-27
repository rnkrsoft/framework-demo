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
