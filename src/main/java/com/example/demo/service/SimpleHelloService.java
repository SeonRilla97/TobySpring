package com.example.demo.service;

import org.springframework.stereotype.Component;

@Component
public class SimpleHelloService implements HelloService {

    @Override
    public String sayHello(String name){
        return "Hello " + name;
    }
}
