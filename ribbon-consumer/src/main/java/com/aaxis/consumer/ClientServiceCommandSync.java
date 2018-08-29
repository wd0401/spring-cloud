package com.aaxis.consumer;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

import java.util.List;

import static com.aaxis.consumer.MyClientServiceCommand.getObjectObservable;

@Service
public class ClientServiceCommandSync extends HystrixObservableCommand {
    @Autowired
    RestTemplate restTemplate;

    private Long id;

    protected ClientServiceCommandSync() {
        super(HystrixCommandGroupKey.Factory.asKey("ClientServiceCommandSync"));
    }


    @Override
    protected Observable<Object> construct() {

        return getObjectObservable(restTemplate);
    }
}
