package com.example.demo.controller;

import com.example.demo.service.HelloService;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * 요청에 대한 검증
 * 응답의 형식 결정
 * 로직 호출
 */
@RequestMapping
public class HelloController {


    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    @ResponseBody
//    @RequestMapping(value="/hello", method= RequestMethod.GET)
    public String hello(String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
