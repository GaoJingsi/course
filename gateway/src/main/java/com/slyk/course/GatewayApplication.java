package com.slyk.course;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    private static Logger log = LoggerFactory.getLogger(GatewayApplication.class);

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(GatewayApplication.class);
        ConfigurableEnvironment environment = springApplication.run(args).getEnvironment();
        log.info(
                "{} 模块： http://{}:{} 启动成功！",
                environment.getProperty("spring.application.name"),
                environment.getProperty("server.address"),
                environment.getProperty("server.port")
        );
    }

}
