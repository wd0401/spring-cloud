package com.aaxis.eurekaserver;

import com.aaxis.dto.User;
import com.aaxis.service.ClientServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class EurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }

}

@RestController
class ServiceInstanceRestController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/service-instances")
    public List<String> serviceInstancesByApplicationName() {
        return this.discoveryClient.getServices();
    }
}

@RestController
class RefactorRestController implements ClientServiceI {

    @Override
    public String hello(@RequestParam("name") String name) {
        return "Hello, " + name;
    }

    @Override
    public String hello(@RequestBody User user) {
        return "Hello, " + user.getName();
    }

    @Override
    public User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age) {
        return new User(name, age);
    }
}

