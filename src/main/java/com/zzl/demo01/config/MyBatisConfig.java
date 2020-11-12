package com.zzl.demo01.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.zzl.demo01.mbg.mapper")
public class MyBatisConfig {
}
