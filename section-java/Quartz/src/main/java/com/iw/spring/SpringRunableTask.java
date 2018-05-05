package com.iw.spring;

public class SpringRunableTask implements Runnable  {
    // TODO: 2018/5/5 0005 使用spring自带的task 来完成任务调度,任务类需要实现Runable 接口

    public void run() {
        System.out.println("spring pool" + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
