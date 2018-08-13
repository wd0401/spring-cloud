package com.aaxis.consumer;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ClientServiceCommand extends HystrixCommand {
    @Autowired
    RestTemplate restTemplate;

    protected ClientServiceCommand() {
        super(HystrixCommandGroupKey.Factory.asKey("ClientServiceCommand"));
    }


    @Override
    protected Object run() throws Exception {
        return restTemplate.getForEntity("http://eureka-client1/service-instances",List.class).getBody();
    }


    @Override
    protected String getFallback() { // run方法抛出异常的时候返回备用结果
        return "ClientServiceCommand Failure ";
    }

}
