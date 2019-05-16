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
