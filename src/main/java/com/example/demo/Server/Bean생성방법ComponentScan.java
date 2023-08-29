package com.example.demo.Server;

import com.example.demo.controller.HelloController;
import com.example.demo.service.HelloService;
import com.example.demo.service.SimpleHelloService;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * EP6
 * Template Method 패턴을 이용한 Bean 생성
 * 관계를 맺을 시점 , 어느 Bean을 주입할건지?
 *
 * 정보를 줘야한다!!
 * Bean을 주입하는 방법은?
 *
 */
@Configuration
@ComponentScan
public class Bean생성방법ComponentScan implements Server {



    @Override
    public void run() {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext(){
            @Override
            protected void onRefresh() {
                super.onRefresh();

                //Servlet
                ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispathcerServlet",
                            new DispatcherServlet(this)).addMapping("/*");
                });
                webServer.start();

            }
        };
//        applicationContext.registerBean(HelloController.class); // Bean 등록
//        applicationContext.registerBean(SimpleHelloService.class); // Bean 등록

        applicationContext.register(Bean생성방법ComponentScan.class);
        //DI는 어떻게...??? ==> 생성자 호출시, 타입을 확인하고 Container의 등록정보를 뒤져서 있으면 던져준다
        applicationContext.refresh(); // Container 초기화




    }
}

/**
 * Configuration은 전체 앱을 구성하는데 필요한 기초 베이스이기 때문에 가장 우선적으로 실행하여 구성한다.
 */
