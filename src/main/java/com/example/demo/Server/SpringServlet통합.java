package com.example.demo.Server;

import com.example.demo.controller.HelloController;
import com.example.demo.service.SimpleHelloService;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
/**
 * EP5
 * Dispatcher Servlet 생성
 *  Bean 등록
 *
 * Application Context를 생성하는 Refresh()의 훅을 이용, Template Method Parttern[서브 클래스 확장]으로 Servlet Container 생성 및 연결
 * Template Method 패턴 : 상속을 통한 기능 확장
 * 그중, Application Context 웹 확장 버전인 GenericWebApplicationContext를 상속 및 확장 구현 하는것으로 커스터마이징
 */
public class SpringServlet통합 implements Server {

    @Override
    public void run() {
        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext(){
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
        applicationContext.registerBean(HelloController.class); // Bean 등록
        applicationContext.registerBean(SimpleHelloService.class); // Bean 등록
        //DI는 어떻게...??? ==> 생성자 호출시, 타입을 확인하고 Container의 등록정보를 뒤져서 있으면 던져준다
        applicationContext.refresh(); // Container 초기화




    }
}
