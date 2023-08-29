package com.example.demo.Server;

import com.example.demo.controller.HelloController;
import com.example.demo.service.HelloService;
import org.springframework.stereotype.Component;

@Component
public class Web없이DI {
    private HelloController helloController;
    private HelloService helloService;

    public Web없이DI(HelloController helloController, HelloService helloService) {
        this.helloController = helloController;
        this.helloService = helloService;

        System.out.println(helloController + "\n" + helloService);
    }
}
