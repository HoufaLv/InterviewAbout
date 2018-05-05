package com.iw.timer;

import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;

import static org.junit.Assert.*;

public class MyTimerTaskTest {

    @Test
    public void run() throws IOException {

        Timer timer = new Timer();
        MyTimerTask myTimerTask = new MyTimerTask();
        MyTimerTask2 myTimerTask2 = new MyTimerTask2();

        //使用delay 参数来延迟执行事件, 使用period 来实现重复执行
        //timer.schedule(myTimerTask,1000,1000);

        //使用Date 参数来指定开始事件,是否延迟等
        //timer.schedule(myTimerTask,new Date(),2000);

        timer.scheduleAtFixedRate(myTimerTask2,0,1000);
        System.in.read();
    }
}