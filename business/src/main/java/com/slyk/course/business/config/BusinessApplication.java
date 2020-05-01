package com.slyk.course.business.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication(scanBasePackages = {"com.slyk.course.business", "com.slyk.course.server"})
@EnableDiscoveryClient
@MapperScan("com.slyk.course.server.mapper")
public class BusinessApplication {

    private static Logger log = LoggerFactory.getLogger(BusinessApplication.class);

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(BusinessApplication.class);
        ConfigurableEnvironment environment = springApplication.run(args).getEnvironment();
        log.info(
                "{} 模块： http://{}:{} 启动成功！",
                environment.getProperty("spring.application.name"),
                environment.getProperty("server-address"),
                environment.getProperty("server.port")
        );
    }

}
