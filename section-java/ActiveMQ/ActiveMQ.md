### JMS-ActiveMQ

> ActiveMQ prot: 8186 
>
> ActiveMQ tcpProt:61616

> Java消息服务（Java Message Service， JMS）应用程序接口是一个Java平台中关于面向消息中间（MOM）的 API，用于在两个应用程序之间，或分布式系统中发送消息，进行异步通信。 Java消息服务是一个与具体平台无关的 API，绝大多数MOM提供商都对JMS提供支持。    
>

##### Java 消息服务的规范包括两种消息模式

- 点对点  - **Queue**
- 发布者/订阅者 -  **Topic**

##### JMS 常用 API

- ConnectionFactory  - **连接工厂**
- Connection - **连接**
- Session - **会话**
- Desination - **目标是一个包装了消息目标标识符的被管对象，消息目标是指消息发布和接收的地点，或者是队列，或者是主题**
- MessageConsumer - **消费者,由会话创建的对象，用于接收发送到目标的消息**
- MessageProducer - **提供者由会话创建的对象，用于发送消息到目标**
- Message - **是在消费者和生产者之间传送的对象，也就是说从一个应用程序创送到另一个应用程序**



#### 点对点消息 (队列模型)

> 在点对点或队列模型下，一个生产者向一个特定的队列发布消息,一个消费者从队列中读取消息,生产者知道消费者的队列,并直接将消息发到消费者的队列

- 只有一个消费者将获得消息

- 生产者不需要在接收者接收消息期间处于运行状态,接收者也不需要在消息发送时处于运行状态

- 每一个成功处理的消息都由接收者签收 

  ![Queue](G:\GitHub\Programme\interview\section-java\ActiveMQ\Queue.png)



#### 发布/订阅 消息(主题模型)

> 发布者/订阅者 模型支持向一个特定的消息主题发布消息,0个或多个订阅者可能对接受来自特定消息主题的消息感兴趣.在这种模型下,发布者和订阅者彼此不知道对方,

- 多个消费者可以获得消息
- 在发布者和订阅者之间存在时间依赖性.发布者需要建立起一个订阅,订阅者必须保持持续的活动状态以接受信息

![topic](G:\GitHub\Programme\interview\section-java\ActiveMQ\topic.png)



### Spring 整合 JMS

#### 点对点 (队列模型)

- dependency

```xml
//需要spring-jms activemq-all 的支持
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>4.3.14.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-test</artifactId>
    <version>4.3.14.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jms</artifactId>
    <version>4.3.14.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.apache.activemq</groupId>
    <artifactId>activemq-all</artifactId>
    <version>5.15.0</version>
</dependency>
```

- 整合配置文件

```xml
    <!--队列模型-->

    <!--1.创建ActiveMQ 连接工厂-->
    <bean id="activeMQConnectionFactory" class="org.apache.activemq.ActiveMQConnectionFactory">
        <property name="brokerURL" value="tcp://localhost:61616"/>
    </bean>
    <!--2.创建spring 连接工厂-->
    <bean id="connectionFactory" class="org.springframework.jms.connection.SingleConnectionFactory">
        <property name="targetConnectionFactory" ref="activeMQConnectionFactory"/>
    </bean>

    <bean id="destination" class="org.apache.activemq.command.ActiveMQQueue">
        <constructor-arg index="0" value="iw-queue"></constructor-arg>
    </bean>

    <!--3.配置JmsTemplate-->
    <bean id="jmsTemplate" class="org.springframework.jms.core.JmsTemplate">
        <property name="connectionFactory" ref="connectionFactory"/>
        <property name="defaultDestination" ref="destination"/>
    </bean>

    <!--4.配置监听器,在容器启动的时候,就会自动去监听队列,如果队列中有消息,就会将消息消化-->
    <bean id="springQueueListener" class="com.iw.consumer.ActiveMQSpringConsumer1"/>

    <!--5.配置容器监听器-->
    <bean id="messageListenerContainer" class="org.springframework.jms.listener.DefaultMessageListenerContainer">
        <property name="connectionFactory" ref="connectionFactory"/>
        <property name="destinationName" value="iw-queue"/>
        <property name="messageListener" ref="springQueueListener"/>
    </bean>
```

- 向队列中发送消息

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-jms-queue-1.xml")
public class QueueTest1 {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Test
    public void sendMessage(){
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("iw-queue3");
            }
        });
    }


    /**
     * 消化队列中的信息,因为在配置文件中配置了监听器,则容器一旦启动,就会监听队列,然后使用 实现了Listerer 的类中的 onMessage 方法来处理队列中的信息
     */
    @Test
    public void consumerMessage() throws IOException {
        System.out.println("spring start!");
        System.in.read();
    }
}
```

- 如何实现 MessageListener

```java
/**
 * 消化队列中的信息
 */
@Component
public class ActiveMQSpringConsumer1 implements MessageListener {

    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            String text = textMessage.getText();
            System.out.println(text);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
```

#### 多线程处理消息队列

- 添加多个监听器

```xml
    <!--队列模型-->

    <!--1.创建ActiveMQ 连接工厂-->
    <bean id="activeMQConnectionFactory" class="org.apache.activemq.ActiveMQConnectionFactory">
        <property name="brokerURL" value="tcp://localhost:61616"/>
    </bean>
    <!--2.创建spring 连接工厂-->
    <bean id="connectionFactory" class="org.springframework.jms.connection.SingleConnectionFactory">
        <property name="targetConnectionFactory" ref="activeMQConnectionFactory"/>
    </bean>

    <!--3.配置JmsTemplate-->
    <bean id="jmsTemplate" class="org.springframework.jms.core.JmsTemplate">
        <property name="connectionFactory" ref="connectionFactory"/>
        <property name="defaultDestination" ref="destination"/>
    </bean>
    <bean id="destination" class="org.apache.activemq.command.ActiveMQQueue">
        <constructor-arg index="0" value="iw-queue"/>
    </bean>
	
	<!--添加多个监听器,可以使用多个实现了MessageListener 的类,多线程处理消息队列-->
    <bean id="listener1" class="com.iw.consumer.ActiveMQSpringListener1"/>
    <bean id="listener2" class="com.iw.consumer.ActiveMQSpringListener2"/>

    <jms:listener-container acknowledge="client" connection-factory="connectionFactory" concurrency="3-9">
        <jms:listener destination="iw-queue" ref="listener1"/>
        <jms:listener destination="iw-queue" ref="listener2"/>
    </jms:listener-container>
```

- 使用多个类实现MessageListener来处理消息队列

```java
/**
 * 实现MessageListener 来处理消息队列
 */
public class ActiveMQSpringListener1 implements MessageListener {

    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            System.out.println("Thred ************* " + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
```

#### 使用注解模式接收Queue信息

- 添加配置文件

```xml
    <!--队列模型-->
    <context:component-scan base-package="com.iw"/>
    
    <!--1.创建ActiveMQ 连接工厂-->
    <bean id="activeMQConnectionFactory" class="org.apache.activemq.ActiveMQConnectionFactory">
        <property name="brokerURL" value="tcp://localhost:61616"/>
    </bean>
    <!--2.创建spring 连接工厂-->
    <bean id="connectionFactory" class="org.springframework.jms.connection.SingleConnectionFactory">
        <property name="targetConnectionFactory" ref="activeMQConnectionFactory"/>
    </bean>

    <!--使用注解来处理消息队列-->
    <bean id="jmsListenerContainerFactory" class="org.springframework.jms.config.DefaultJmsListenerContainerFactory">
        <property name="connectionFactory" ref="connectionFactory"/>
        <property name="concurrency" value="3-9"/>
    </bean>

    <!--3.配置JmsTemplate-->
    <bean id="jmsTemplate" class="org.springframework.jms.core.JmsTemplate">
        <property name="connectionFactory" ref="connectionFactory"/>
        <property name="defaultDestinationName" value="iw-queue"/>
    </bean>

    <!--添加该配置-->
    <jms:annotation-driven container-factory="jmsListenerContainerFactory"/>
```

- 使用注解来标记方法处理队列信息

```java
/**
 * 使用注解的方式处理队列中的信息
 */
@Component
public class ActiveMQSpringAnnotation {
    /*
    * 使用@JmsListener 注解来表明使用注解来处理消息队列
    */
    @JmsListener(destination = "iw-queue")
    public void queueMessageConsumer(Message message) throws JMSException {
        TextMessage textMessage = (TextMessage) message;
        System.out.println("队列消息内容" + textMessage.getText());
    }
}
```

- 当容器启动后,会自动去消化队列中的消息

```java
@RunWith(SpringJUnit4ClassRunner.class)
//配置文件别读错了
@ContextConfiguration(locations = "classpath:spring-jms-queue-3.xml")
public class QueueAnnotaionTest {

    /**
     * 消化队列中的信息,容器会自动调用使用 @JmsListener 注解标记的方法
     */
    @Test
    public void consumerMessage() throws IOException {
        System.out.println("spring start!");
        System.in.read();
    }
}
```

####发布订阅模型(topic) 

> 和队列模型差不多,只不过需要在配置文件中指定topic模式和更改消息类型







