package com.tk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author: TK
 * @date: 2021/4/16 22:35
 */
@EnableEurekaServer
@SpringBootApplication
public class Master {
    public static void main(String[] args) {
        SpringApplication.run(Master.class,args);
    }
}
