package com.demo.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class JulyAspect {
    @Before("execution(public void com.demo.spring.service.UserService.sayHello())")
    public void julyBefore(JoinPoint joinPoint){
        System.out.println("aopMethod:julyBefore");
    }
}
