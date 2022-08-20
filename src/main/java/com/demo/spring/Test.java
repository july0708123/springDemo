package com.demo.spring;

import com.demo.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        //Bean对象和new出来的普通对象的区别
        UserService userService = (UserService) context.getBean("userService");
        userService.sayHello();

        //对象的依赖注入伪代码，判断哪些属性加了Autowired注解
//        for(Field field:userService.getClass().getFields()){
//            if(field.isAnnotationPresent(Autowired.class)){
//                field.set(userService,??);
//            }
//        }

        //初始化前，spring中的类似实现
//        for(Method method:userService.getClass().getMethods()){
//            if (method.isAnnotationPresent(PostConstruct.class)){
//                method.invoke(userService,??);
//            }
//        }

    }
}
