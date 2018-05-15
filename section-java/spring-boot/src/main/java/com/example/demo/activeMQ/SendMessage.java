package com.example.demo.activeMQ;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

@Component
public class SendMessage {

    @Autowired
    public JmsTemplate jmsTemplate;


    /**
     * 向MQ 队列中推送消息
     */
    public void sendMessgeToQueue(String message){
        ActiveMQQueue activeMQQueue = new ActiveMQQueue("spring-boot queue");
        jmsTemplate.send(activeMQQueue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(message);
                return textMessage;
            }
        });
    }

    /**
     * 向MQ 主题中推送消息
     */
    public void sendMessageToTopic(String message){
        ActiveMQTopic activeMQTopic = new ActiveMQTopic("spring-boot topic");
        jmsTemplate.send(activeMQTopic, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(message);
                return textMessage;
            }
        });
    }


}
