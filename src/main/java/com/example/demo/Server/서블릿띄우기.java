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
 * EP1
 * 서블릿 컨테이너를 띄우고(서버 정보를 불러와서, 서버 인스턴스를 만든다)
 *
 * 서블릿을 등록한다(서블릿의 Mapping 정보, 로직, Response Header 만들기)
 */
public class 서블릿띄우기 implements Server {
    @Override
    public void run() {
        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            HelloService helloService = new SimpleHelloService();
            HelloController helloController = new HelloController(helloService);
            servletContext.addServlet("hello", new HttpServlet() {

                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

                    String name = req.getParameter("name");



                    resp.setStatus(HttpStatus.OK.value());
                    resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                    resp.getWriter().println("Hello Servlet!" + name);
                }
            }).addMapping("/hello");

            servletContext.addServlet("Bye", new HttpServlet(){
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    resp.setStatus(HttpStatus.OK.value());
                    resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                    resp.getWriter().println("Bye Servlet!");
                }
            }).addMapping("/bye");
        });
        webServer.start();
    }
}
/**
 * 서블릿마다 중복되는 코드는 어떻게 처리하지? (공통 코드 처리)
 * Requset 와 Response를 직접 만지는건 부자연스럽지 않을까?(왜 부자연스럽지?)
 */