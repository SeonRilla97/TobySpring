package com.example.demo.Server;

import com.example.demo.controller.HelloController;
import com.example.demo.service.HelloService;
import com.example.demo.service.SimpleHelloService;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * EP2
 * 요청을 받아 분배
 * 공통 코드 처리 (인증, 보안, 다국어 등등)
 */
public class 프론트컨트롤러 implements Server{

    @Override
    public void run() {
        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            servletContext.addServlet("frontController", new HttpServlet() {
                HelloService helloService = new SimpleHelloService();
                HelloController helloController = new HelloController(helloService);

                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    //전처리

                    //mapping
                    if(req.getRequestURI().equals("/hello")){
                        String name = req.getParameter("name");
                        String hello = helloController.hello(name);


                        resp.setStatus(HttpStatus.OK.value());
                        resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);

                        resp.getWriter().println("Hello Servlet!" + hello);

                    }

                    else if(req.getRequestURI().equals("/user")){
                        //
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
 * 하나의 Front Controller가 공통 기능을 제공한 후, Handler 호출을 통해 요청을 처리한다.
 * 기능이 여기서 빠져야한다. (name parameter를 받아 가공후, 응답에 보내는코드는 로직[기능]이다. -> Controller에게 위임)
 * --> 하지만 Request와 Response는 어떻게 전달할건지?
 *
 * binding : 웹 요청으로부터 기본 데이터 타입으로 변환후 넘겨주는것
 * Mapping : 요청에 알맞은 Component를 호출하는것
 */