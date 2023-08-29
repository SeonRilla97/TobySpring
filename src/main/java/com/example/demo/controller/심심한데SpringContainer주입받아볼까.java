package com.example.demo.controller;

import com.example.demo.service.HelloService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * EP7-1
 * ApplicationContextAware를 구현하면서 ApplicationContext를 setter로 받아도 되지만, 옛날 방식
 * 그냥ComponentScan 시켜도 주입할 수 있다.
 */
public class 심심한데SpringContainer주입받아볼까 {


    private final HelloService helloService;
    private ApplicationContext applicationContext;

    public 심심한데SpringContainer주입받아볼까(HelloService helloService, ApplicationContext applicationContext) {
        this.helloService = helloService;
        this.applicationContext = applicationContext;
        System.out.println("이거는 찾아요??");
        System.out.println(applicationContext);
    }

    @GetMapping("/hello")
    @ResponseBody
//    @RequestMapping(value="/hello", method= RequestMethod.GET)
    public String hello(String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }

}
