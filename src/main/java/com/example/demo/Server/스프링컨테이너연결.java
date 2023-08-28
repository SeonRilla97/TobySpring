package com.example.demo.Server;

import com.example.demo.controller.HelloController;
import com.example.demo.service.SimpleHelloService;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * EP3
 * Spring Container 생성
 *  Bean 등록
 */
public class 스프링컨테이너연결 implements Server {

    @Override
    public void run() {

        //Spring
                //
        GenericApplicationContext applicationContext = new GenericApplicationContext(); //Spring Container 구성정보
        applicationContext.registerBean(HelloController.class); // Bean 등록
        applicationContext.registerBean(SimpleHelloService.class); // Bean 등록
        //DI는 어떻게...??? ==> 생성자 호출시, 타입을 확인하고 Container의 등록정보를 뒤져서 있으면 던져준다
        applicationContext.refresh(); // Container 초기화



        //Servlet
        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            servletContext.addServlet("frontController", new HttpServlet() {

                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    //전처리

                    //mapping
                    if(req.getRequestURI().equals("/hello")){
                        String name = req.getParameter("name");

                       //Spring Bean 가져오기
                        HelloController helloController = applicationContext.getBean(HelloController.class);
                        String hello = helloController.hello(name);

                        resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
                        resp.getWriter().println("Hello Servlet!" + hello);
                    }
                    else {
                        resp.setStatus(HttpStatus.NOT_FOUND.value());
                    }
                }
            }).addMapping("/*");
        });
        webServer.start();
    }
}
/**
 * Servlet Container는 HelloController가 어떻게 제작되었는지 모름
 *
 * SPring Container => Singleton Registry( 하나의 Object를 만들어두고 재사용한다)
 */