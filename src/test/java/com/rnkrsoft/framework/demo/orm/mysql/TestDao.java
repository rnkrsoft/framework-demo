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
        //MySQL ֧����������
        //1.��ʵ����ָ����������ֵ
        //2.ʹ��UUID�Զ����ɣ���ʵ�������ȡ����ֵ
        //3.ʹ��MySQL�������������������ʵ�������ȡ����ֵ
        //4.ʹ��SequenceService�ṩ��������������ֵ����ʵ�������ȡ����ֵ
        //5.ʹ�ñ��ʽ�ṩ����ֵ��ֻ�����ַ������͵�����ֵ��֧�ֱ��ʽΪ
        //      a.�̶��ַ���
        //      b.${yyyyMMddHHmmssSSS} ���ڱ������ʽ
        //      c.${SEQ:����} ʹ��SequenceService�ṩ����������ţ�������������ָ�����ȵ�
        //      d.${RANDOM:����} ʹ�����ֵ��������ָ�����ȵ����
        TbAgentInfoEntity entity = new TbAgentInfoEntity();
        entity.setAgentName("xxx");
        entity.setAddress("xxx");
        entity.setPhone("xxx");
        entity.setAddUserId("xxx");
        entity.setUpdateUserId("xxx");
        tbAgentInfoDAO.insertSelective(entity);

        tbAgentInfoDAO.selectAll();
    }
}
