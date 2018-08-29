package com.aaxis.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@FeignClient("eureka-client1")
public interface ClientService {

    @RequestMapping("/service-instances")
    List<String> serviceInstancesByApplicationName();
}
