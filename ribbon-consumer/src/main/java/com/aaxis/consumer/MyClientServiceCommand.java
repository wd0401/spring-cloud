package com.aaxis.consumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MyClientServiceCommand {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallBack")
    public Object execute() {
        return restTemplate.getForEntity("http://eureka-client1/service-instances", List.class).getBody();
    }

    public Object fallBack() {
        return "failed!!!";
    }
}
