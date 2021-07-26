package com.tk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author: TK
 * @date: 2021/4/17 10:02
 */
@EnableEurekaServer
@SpringBootApplication
public class Slave {

  public static void main(String[] args) {
    SpringApplication.run(Slave.class,args);
  }
}
