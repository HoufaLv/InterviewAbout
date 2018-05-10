package com.example.demo.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("经过监听器");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
