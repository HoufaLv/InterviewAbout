## 任务调度 [定时任务] [计划任务]
- 调度系统最核心的概念有两个: **触发,依赖**
- 触发: 满足某条件后,执行相应的任务,可以有**时间触发,事件触发**
- 依赖是指某个任务执行之前,必须满足某个条件
- 触发是**充分条件**,依赖是**必要条件**

### java 任务调度实现
- **java.util.TimerTask**类, 继承**TimerTask**类来实现任务调度,使用**Timer**类来安排计划

### Quartz 任务调度实现
- 依赖
```
<!--Quartz-->
<dependency>
    <groupId>org.quartz-scheduler</groupId>
    <artifactId>quartz</artifactId>
    <version>2.2.1</version>
</dependency>
<dependency>
    <groupId>org.quartz-scheduler</groupId>
    <artifactId>quartz-jobs</artifactId>
    <version>2.2.1</version>
</dependency>
```
- 实现**org.quartz.Job**接口创建 任务
```
public class MyJob implements Job {

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("quartz mission");
    }
}
```
##### 使用SimpleTrigger 调度任务
```
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
```
##### 使用CronTrigger 调度任务
```
  //使用CronTriiger 来实现任务调度,间隔1秒  
  JobDetail jobDetail = JobBuilder.newJob(MyJob.class).build();
  CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/1 * * * * ? *");
  CronTrigger cronTrigger = TriggerBuilder.newTrigger().withSchedule(cronScheduleBuilder).build();

  Scheduler scheduler = new StdSchedulerFactory().getScheduler();
  scheduler.scheduleJob(jobDetail,cronTrigger);
  scheduler.start();

  System.in.read();
```
