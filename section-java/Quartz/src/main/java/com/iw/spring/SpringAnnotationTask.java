package com.iw.spring;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 基于注解的spring task
 * @author Lvhoufa
 */
@Component
public class SpringAnnotationTask {
    // TODO: 2018/5/5 0005 完成基于注解的spring task

    /**
     * 通过cron 表达式定义规则
     */
    //@Scheduled(cron = "0/3 * * * * ?")
    public void cronJob(){
        System.out.println("SpringAnnotationTask.cronJob");
    }

    /**
     * 上一次执行完毕时间点之后1秒再执行
     */
    //@Scheduled(fixedDelay = 3000)
    public void delayJob(){
        System.out.println("SpringAnnotationTask.delayJob");
    }

    /**
     * 上一次开始执行时间点之后1秒再执行
     */
    //@Scheduled(fixedRate = 1000)
    public void rateJob(){
        System.out.println("SpringAnnotationTask.rateJob");
    }

    /**
     * 异步于同步
     * 为啥需要异步,因为不能等一件事做完之后再做一件事,需要同时开始
     */
    //@Async
    //@Scheduled(fixedRate = 1000)
    public void asyncTask(){
        System.out.println("SpringAnnotationTask.asyncTask->" + Thread.currentThread().getName());
    }
}
