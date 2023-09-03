package com.example.demo.controller;

import com.example.demo.service.HelloService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * 요청에 대한 검증
 * 응답의 형식 결정
 * 로직 호출
 */
@RequestMapping
@Component
public class HelloController implements ApplicationContextAware {


    private final HelloService helloService;
    private ApplicationContext applicationContext;

    //    @RequestMapping(value="/hello", method= RequestMethod.GET)
    public HelloController(HelloService helloService) {
        System.out.println("컨트롤러 넣니?");
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello(String name) {
         if(name==null || name.trim().length()==0) throw new IllegalArgumentException();
        return helloService.sayHello(name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(applicationContext);
        this.applicationContext = applicationContext;
    }
}
