package com.cht.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
//@ComponentScan({"com.cht.springcloud.service","com.cht.springcloud.controller"})
public class DeptConsumer_80_Feign {

    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer_80_Feign.class,args);
    }
}
