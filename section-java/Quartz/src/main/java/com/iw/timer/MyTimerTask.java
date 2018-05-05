package com.iw.timer;

import java.util.TimerTask;

/**
 * 使用jdk 来实现任务调度
 * @author Lvhoufa
 */
public class MyTimerTask extends TimerTask {

    // TODO: 2018/5/5 0005 继承 TimerTask 类来实现任务调度,使用 Timer 类来启用任务调度

    @Override
    public void run() {
        System.out.println("执行任务");
    }
}
