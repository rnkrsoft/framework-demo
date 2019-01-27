package com.rnkrsoft.framework.demo.sequence;

import com.rnkrsoft.framework.sequence.SequenceService;
import com.rnkrsoft.framework.sequence.SequenceServiceFactory;
import com.rnkrsoft.utils.DateUtils;
import org.junit.Test;

/**
 * Created by rnkrsoft.com on 2019/1/27.
 */
public class SequenceTest {
    @Test
    public void test1() {
        SequenceService sequenceService = SequenceServiceFactory.instance();
        long l1 = sequenceService.nextval("", "", "user_id", DateUtils.getCurrDate());
        System.out.println(l1);
        long l2 = sequenceService.curval("", "", "user_id", DateUtils.getCurrDate());
        System.out.println(l2);
    }
}
