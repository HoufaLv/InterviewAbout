package com.example.demo.controller;

import com.example.demo.activeMQ.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JmsController {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private SendMessage sendMessage;

    /**
     * 向Mq queue中推送消息
     * @return
     */
    @GetMapping("/jsmq")
    public String sendMessageToQueue(){
        sendMessage.sendMessgeToQueue("hello" + System.currentTimeMillis());
        return "Already send message to queue";
    }

    /**
     * 向Mq topic中推送消息
     * @return
     */
    @GetMapping("/jsmt")
    public String sendMessageToTopic(){
        sendMessage.sendMessageToTopic("heello" + System.currentTimeMillis());
        return "Already send message to topic";
    }

}
