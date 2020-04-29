package com.slyk.course.eureka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    private static Logger log = LoggerFactory.getLogger(EurekaServerApplication.class);

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(EurekaServerApplication.class);
        ConfigurableEnvironment environment = springApplication.run(args).getEnvironment();
        log.info(
                "{} 模块： http://{}:{} 启动成功！",
                environment.getProperty("spring.application.name"),
                environment.getProperty("server.address"),
                environment.getProperty("server.port")
        );
    }

}
