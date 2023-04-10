package com.lsjc.beta;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @program: demo
 * @description: 启动类
 * @author: zhang zl
 * @created: 2023/03/31 17:13
 */
@Configuration
@EnableConfigurationProperties
@SpringBootApplication
@MapperScan("com.lsjc.beta.mapper")
public class BootStrapApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootStrapApplication.class, args);
    }
}
