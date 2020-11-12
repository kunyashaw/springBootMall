package com.zzl.demo01;

import com.zzl.demo01.api.CommonResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;

@SpringBootApplication
public class Demo01Application {

    public static void main(String[] args) {
        try{
            SpringApplication.run(Demo01Application.class, args);
        }catch (Exception e){
             CommonResult.validateFailed("缺少参数:"+e.getMessage());
        }
    }




}
