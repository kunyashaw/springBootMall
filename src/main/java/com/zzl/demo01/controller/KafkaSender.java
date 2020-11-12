package com.zzl.demo01.controller;

import cn.hutool.core.date.TimeInterval;
import cn.hutool.cron.CronUtil;
import cn.hutool.crypto.digest.mac.MacEngine;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.mysql.cj.util.LogUtils;
import com.zzl.demo01.api.CommonResult;
import com.zzl.demo01.component.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import java.util.Date;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

@Api(tags = "kafkaProvider",description = "消息队列生成者")
@RestController
public class KafkaSender {
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @ApiOperation("发送消息")
    @RequestMapping(value = "/send",method = RequestMethod.GET)
    CommonResult send(@RequestParam("msg") String msg){


        Message msgInstance = new Message();
        msgInstance.setId(1L);
        msgInstance.setMsg(msg);
        msgInstance.setSendTime(new Date());
        kafkaTemplate.send("kafka_one", JSONUtil.toJsonStr(msgInstance));
        return CommonResult.success(msgInstance);
    }

    @KafkaListener(topics = "kafka_one")
    public void listen(ConsumerRecord<?,?> record){
        Optional<?> kafkaMsg = Optional.ofNullable(record.value());
        if(kafkaMsg.isPresent()){
            new Timer().schedule(new TimerTask() {
                public void run() {
                    System.out.print(kafkaMsg.get());
                }
            }, 10000);

        }
    }
}


