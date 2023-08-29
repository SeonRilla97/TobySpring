package com.example.demo.Server;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * EP8 Fin
 * Spring Container 생성과 DispatcherServlet 생성을 Bean으로 등록하자!
 *
 * 문제 발생
 * 1. dispatcherServlet 만들 때 Spring Container를 넘겨줘야 하는데 아직 application Context 만들어지는 도중에, 어떻게 정보를 넘겨주지?? (Bean을 사용하기 이전인데?)
 *
 */


public class 최종버전 {
    public static void run(Class<?> applicationClass , String... args) {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext(){
            @Override
            protected void onRefresh() {
                super.onRefresh();

                //Servlet
                ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
                dispatcherServlet.setApplicationContext(this);

                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispathcerServlet", dispatcherServlet).addMapping("/*");
                });
                System.out.println("부트 스타트!");
                webServer.start();

            }
        };

        applicationContext.register(applicationClass);
        applicationContext.refresh(); // Container 초기화
    }
}