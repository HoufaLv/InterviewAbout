package com.iw.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-task.xml")
public class SpringTaskTest {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Test
    public void test() throws IOException {
        SpringRunableTask springTask = new SpringRunableTask();
        threadPoolTaskScheduler.schedule(springTask,new CronTrigger("0/1 * * * * *"));

        System.in.read();
    }

}