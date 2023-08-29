package com.example.demo.Server;

import com.example.demo.controller.HelloController;
import com.example.demo.service.HelloService;
import com.example.demo.service.SimpleHelloService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Controller;

@ComponentScan
public class 발표자료_ServletCotanier가필요할까 {

    public static void run() {
        GenericApplicationContext applicationContext = new GenericApplicationContext(); //Spring Container 구성정보


        applicationContext.registerBean(HelloController.class); // Bean 등록
        applicationContext.registerBean(SimpleHelloService.class); // Bean 등록
        applicationContext.registerBean(Web없이DI.class); // Bean 등록


        //DI는 어떻게...??? ==> 생성자 호출시, 타입을 확인하고 Container의 등록정보를 뒤져서 있으면 던져준다
        applicationContext.refresh(); // Container 초기화
    }
}
