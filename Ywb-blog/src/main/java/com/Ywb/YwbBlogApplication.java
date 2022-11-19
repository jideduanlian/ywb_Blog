package com.Ywb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.ywb.domain.mapper")
@EnableScheduling
@EnableSwagger2
public class YwbBlogApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(YwbBlogApplication.class, args);
    }
}
