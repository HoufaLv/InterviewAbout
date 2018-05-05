package com.iw.quartz;

import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;

import static org.junit.Assert.*;

public class MyJobTest {

    @Test
    public void jdbTestCase() throws SchedulerException, IOException {
        //1.创建任务
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).build();
        //2.定义触发形式
        SimpleScheduleBuilder sb = SimpleScheduleBuilder.simpleSchedule();
        //设置时间间隔
        sb.withIntervalInSeconds(5);
        //永远重复
        sb.repeatForever();
        //3.创建触发器
        Trigger trigger = TriggerBuilder.newTrigger().withSchedule(sb).build();
        //4.创建任务调度者对象
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        //5.调度任务,需要任务和触发器两个参数
        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.start();

        System.in.read();
    }
}