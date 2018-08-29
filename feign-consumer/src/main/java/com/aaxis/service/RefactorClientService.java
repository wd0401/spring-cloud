package com.aaxis.service;

import org.springframework.cloud.openfeign.FeignClient;


@FeignClient("eureka-client1")
public interface RefactorClientService extends ClientServiceI{

}
