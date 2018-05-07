package com.iw.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-task-annotation.xml")
public class SpringAnnotationTaskTest {

    /**
     * 使用配置文件开始
     */
    @Test
    public void start() throws IOException {

        System.in.read();
    }
}