package com.songfeifan.samples.sbt.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.songfeifan.samples.sbt.dao")
public class MybatisConfig {



}
