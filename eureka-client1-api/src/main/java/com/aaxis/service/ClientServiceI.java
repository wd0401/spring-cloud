package com.aaxis.service;

import com.aaxis.dto.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("refactor")
public interface ClientServiceI {
    @RequestMapping("/hello1")
    public String hello(@RequestParam("name") String name);

    @RequestMapping("/hello3")
    public String hello(@RequestBody User user);

    @RequestMapping("/hello2")
    public User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age);
}

