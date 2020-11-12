package com.zzl.demo01.component;

import cn.hutool.core.date.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



@Component
public class MyTask {

    Logger logger = LoggerFactory.getLogger(MyTask.class);

//    Seconds Minutes Hours DayofMonth Month DayofWeek
/**
 * ，枚举 比如Minutes中5,10 在5分和10分各触发一次
 * - 范围 比如Minutes中5-10 在5分和10分 每分钟各触发一次
 * / 固定间隔 比如Minutes中5/10，5分时触发一次，每10分钟触发一次
 * * 任意 比如Minutes中*，每分钟触发一次
 * ？月和周 等同于*
 * # 只用于DayOfWeek 第几个周几 1#3 第三个周日
 * L 最后
 * W 有效工作日（周一到周五）
 * */
    @Scheduled(cron = "0 0/10 * ? * ?")
    private void doSth(){
        logger.info("定时任务执行中"+ DateUtil.current(true));
    }
}
