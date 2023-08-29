package com.example.demo.Server;

import com.example.demo.controller.HelloController;
import com.example.demo.service.SimpleHelloService;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * EP4
 * Dispatcher Servlet 생성
 *  Bean 등록
 *  
 * Application Context 생성 후, Dispatcher Servlet 을 담고있는 Servlet Container 제작
 */
@Component
public class DispatcherServlet사용 implements Server {

    @Override
    public void run() {

        //Spring
                //
//        GenericApplicationContext applicationContext = new GenericApplicationContext(); //Spring Container 구성정보
        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
        applicationContext.registerBean(HelloController.class); // Bean 등록
        applicationContext.registerBean(SimpleHelloService.class); // Bean 등록
        //DI는 어떻게...??? ==> 생성자 호출시, 타입을 확인하고 Container의 등록정보를 뒤져서 있으면 던져준다
        applicationContext.refresh(); // Container 초기화



        //Servlet
        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            servletContext.addServlet("dispathcerServlet",
                    new DispatcherServlet(applicationContext)).addMapping("/*");
        });
        webServer.start();
    }
}
/**
 * dispatcher Servlet이 Mapping 정보를 여기서 어떻게 알지? -> Mapping 정보를 Servlet말고 Spring Bean 에다가 삽입하자!!
 * 
 * 
 * Spring Boot 는 Application Context 만드는 중에, Servlet Container도 같이 제작한다
 */