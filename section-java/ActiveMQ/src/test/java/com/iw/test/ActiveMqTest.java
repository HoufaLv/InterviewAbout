package com.iw.test;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;
import java.io.IOException;

public class ActiveMqTest {

    @Test
    public void provierMessageTestCase(){
        try {
            //1.创建连接工厂
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            //2.创建连接并开启
            Connection connection = connectionFactory.createConnection();
            connection.start();
            //3.创建session, 如果要使用事务,则需要手动提交才可以传递消息, 使用手动签收模式
            Session session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);

            //4.创建消息目的地
            Destination destination = session.createQueue("weixin-Queue");
            //5.创建生产者
            MessageProducer producer = session.createProducer(destination);

            //持久模式,使用该模式后,重启ActiveMQ, 队列中的消息依然存在
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            //6.发送消息
            TextMessage textMessage = session.createTextMessage("hello,MQ-4-DeliveryMode");
            producer.send(textMessage);

            //手动提交 / 回滚事务
            session.commit();

            //7.释放资源
            releaseResource(connection,session,producer);
            System.in.read();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void consumerMessageTestCase(){

        try {
            //1.创建连接工厂
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
            //2.创建连接并开启
            Connection connection = connectionFactory.createConnection();
            connection.start();
            //3.创建session
            Session session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);
            //4.创建消息目的地
            Destination destination = session.createQueue("weixin-Queue");
            //5.创建消费者
            MessageConsumer consumer = session.createConsumer(destination);
            //6.获取消息
            consumer.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        String text = textMessage.getText();
                        System.out.println(text);
                        //手动签收信息
                        textMessage.acknowledge();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });

            System.in.read();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放资源
     * @param connection        连接
     * @param session           会话
     * @param producer          生产者
     */
    public void releaseResource(Connection connection, Session session, MessageProducer producer){
        if (connection!=null){
            try {
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            } finally {
                if (session!=null){
                    try {
                        session.close();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    } finally {
                        if (producer!=null){
                            try {
                                producer.close();
                            } catch (JMSException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }
}
