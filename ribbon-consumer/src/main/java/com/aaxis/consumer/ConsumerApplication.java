package com.aaxis.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

//@SpringBootApplication
//@EnableDiscoveryClient
//@EnableCircuitBreaker
@SpringCloudApplication
public class ConsumerApplication {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}


@RestController
@Slf4j
class ConsumerController {
    @Autowired
    private ClientServiceCommand clientServiceCommand;

    @Autowired
    private MyClientServiceCommand myClientServiceCommand;

    @RequestMapping(value = "/ribbon-consumer",method = RequestMethod.GET)
    public Object helloConsumer() throws Exception {
        return  myClientServiceCommand.execute();
    }

    @RequestMapping(value = "/ribbon-consumer-sync",method = RequestMethod.GET)
    public Object helloConsumerSync() throws Exception {
        return  myClientServiceCommand.getServiceInstance().get();
    }


     @RequestMapping(value = "/ribbon-consumer-observable",method = RequestMethod.GET)
    public Object getInstance() throws Exception {
         Observable<Object> objectObservable = myClientServiceCommand.getInstance();
        log.info("{}",objectObservable.subscribe());

        return  myClientServiceCommand.getInstance();
    }




}