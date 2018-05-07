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

### Cron表达式
> s m h d m w y -> 对应cron表达式中的 *

### Spring Quartz
##### 基于Runable 接口的spring task
- 需要在配置文件中创建 Bean 节点
```
<bean id="poolTaskScheduler" class="org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler">
    <property name="poolSize" value="6"/>
    <property name="threadNamePrefix" value="spring-task-thred-"/>
</bean>
```
- 类需要实现Runable 接口
- 执行任务调度
```
  @RunWith(SpringJUnit4ClassRunner.class)
  @ContextConfiguration(locations = "classpath:spring-task.xml")
  public class SpringTaskTest {

      //需要注入在配置文件中配置的类
      @Autowired
      private ThreadPoolTaskScheduler threadPoolTaskScheduler;

      @Test
      public void test() throws IOException {

          //创建任务类
          SpringRunableTask springTask = new SpringRunableTask();
          threadPoolTaskScheduler.schedule(springTask,new CronTrigger("0/1 * * * * *"));

          System.in.read();
      }

  }
```
- 几种不同的调用方案
```
  //立即执行任务
  taskScheduler.execute(new RunableImpl());

  //在指定时间执行任务
  taskScheduler.schedule(new RunableImpl(),new Date(System.currentTimeMillis()+3000));

  //延迟循环执行任务
  taskScheduler.scheduleWithFixedDelay(new RunableImpl(),10000);

  //从指定的时间开始延迟循环执行任务
  taskScheduler.scheduleWithFixedDelay(new RunableImpl(),new Date(),1000);

  //按照固定间隔执行任务
  taskScheduler.scheduleAtFixedRate(new RunableImpl(),1000);

  //从指定时间按照固定间隔执行任务
  taskScheduler.scheduleAtFixedRate(new RunableImpl(),new Date(),1000);

  //基于Cron表达式执行任务
  taskScheduler.schedule(new RunableImpl(),new CronTrigger("0/5 * * * * *"));

  //使用PeriodicTrigger执行任务
  //每2秒执行一次任务，首次延迟2秒
  PeriodicTrigger periodicTrigger = new PeriodicTrigger(2,TimeUnit.SECONDS);
  periodicTrigger.setInitialDelay(2);
  taskScheduler.schedule(new RunableImpl(),periodicTrigger);
```

##### 基于注解的 Spring task
- 在配置文件中添加自动扫描,定义调度者,开启基于注解的任务调度.
```
  <!--开启自动扫描-->
  <context:component-scan base-package="com.iw"/>
  <!--定义调度者-->
  <task:scheduler id="scheduler" pool-size="10"/>
  <!--开启基于注解的任务调度-->
  <task:annotation-driven scheduler="scheduler"/>
```
-
