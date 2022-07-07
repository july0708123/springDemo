package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("/demo")
public class DemoController {
    @RequestMapping("/test")
    @ResponseBody
    public String testDemo(){
        HashMap hashMap = new HashMap();
        Hashtable hashtable = new Hashtable();
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        System.out.println("this is a demo~~~~~");
        return "demo demo";
    }


}
