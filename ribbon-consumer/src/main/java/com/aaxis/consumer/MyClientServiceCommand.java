package com.aaxis.consumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

import java.util.List;
import java.util.concurrent.Future;

@Service
public class MyClientServiceCommand {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallBack")
    public Object execute() {
        return restTemplate.getForEntity("http://eureka-client1/service-instances", List.class).getBody();
    }


    @HystrixCommand(fallbackMethod = "fallBack")
    public Future<Object> getServiceInstance(){

        return new AsyncResult<Object>() {
            @Override
            public Object invoke() {
                return restTemplate.getForEntity("http://eureka-client1/service-instances", List.class).getBody();
            }
        };
    }

    @HystrixCommand(fallbackMethod = "fallBack")
    public Observable<Object> getInstance(){
        return getObjectObservable(restTemplate);
    }

    static Observable<Object> getObjectObservable(RestTemplate restTemplate) {
        return Observable.create(subscriber -> {
            try {
                if (!subscriber.isUnsubscribed()) {
                    List<String> services = restTemplate.getForEntity("http://eureka-client1/service-instances", List.class).getBody();
                    subscriber.onNext(services);
                    subscriber.onCompleted();
                }
            } catch (Exception e) {

            }
        });
    }


    public Object fallBack() {
        return "failed!!!";
    }
}
