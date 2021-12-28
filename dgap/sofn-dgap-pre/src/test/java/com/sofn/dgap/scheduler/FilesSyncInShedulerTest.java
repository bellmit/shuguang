package com.sofn.dgap.scheduler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by helong on 17-8-3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:Spring-testconfig.xml")
public class FilesSyncInShedulerTest {

    @Autowired
    private FilesSyncInSheduler scheduler;

    @Test
    public void timerHandle() throws Exception {
        scheduler.timerHandle();
    }

}