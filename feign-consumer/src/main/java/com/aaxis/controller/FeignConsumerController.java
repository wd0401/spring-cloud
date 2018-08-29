package com.aaxis.controller;

import com.aaxis.dto.User;
import com.aaxis.service.ClientService;
import com.aaxis.service.RefactorClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FeignConsumerController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private RefactorClientService refactorClientService;

    @RequestMapping(value = "/feign-consumer", method = RequestMethod.GET)
    public List<String> feignServiceInstance() {
        return clientService.serviceInstancesByApplicationName();
    }

    @RequestMapping(value = "/feign-consumer2", method = RequestMethod.GET)
    public String feignServiceInstance2() {
        StringBuilder sb = new StringBuilder();
        sb.append(refactorClientService.hello("MIMI")).append("\n");
        sb.append(refactorClientService.hello("MIMI", 20)).append("\n");
        sb.append(refactorClientService.hello(new User("M工MI", 20))).append("\n");
        return sb.toString();
    }


}
