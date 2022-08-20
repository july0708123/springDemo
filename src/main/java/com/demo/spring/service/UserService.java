package com.demo.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserService {

    @Autowired
    private OrderService orderService;

    //启动的时候想获取mysql中的管理员
    //加Component和Autowired拿到的只是简单的user对象，不是管理员
    //我们应该用自己的逻辑去实现，比如加a()方法，再初始化前调用
    //@Autowired
    private User admin;

    public void sayHello(){
        System.out.println("hello,spring ");
    }

    @PostConstruct
    public void a(){
        //mysql-->User对象-->admin
        System.out.println("method a()");
    }
}
