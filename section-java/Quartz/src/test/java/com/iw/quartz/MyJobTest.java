package com.iw.quartz;

import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import java.io.IOException;

public class MyJobTest {

    @Test
    public void  simpleTriggerTestCase() throws SchedulerException, IOException {
        // TODO: 2018/5/5 0005 使用simpleTrigger 来实现任务调度,间隔5秒

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

    @Test
    public void cronTriggerTestCase() throws SchedulerException, IOException {
        // TODO: 2018/5/5 0005 使用CronTriiger 来实现任务调度,间隔1秒

        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).build();
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/1 * * * * ? *");
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withSchedule(cronScheduleBuilder).build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.scheduleJob(jobDetail,cronTrigger);
        scheduler.start();

        System.in.read();
    }
}