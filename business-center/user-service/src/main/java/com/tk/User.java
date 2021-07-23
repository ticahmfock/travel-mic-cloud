package com.tk;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author: TK
 * @date: 2021/7/23 9:07
 */
@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
public class User {

  public static void main(String[] args) {
    SpringApplication.run(User.class,args);
  }
}
