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
 * EP7
 * Spring Container 생성과 DispatcherServlet 생성을 Bean으로 등록하자!
 *
 * 문제 발생
 * 1. dispatcherServlet 만들 때 Spring Container를 넘겨줘야 하는데 아직 application Context 만들어지는 도중에, 어떻게 정보를 넘겨주지?? (Bean을 사용하기 이전인데?)
 *
 */
@Configuration
@ComponentScan
public class Servlet을Bean등록하기 implements Server {

    @Bean
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    @Override
    public void run() {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext(){
            @Override
            protected void onRefresh() {
                super.onRefresh();

                //Servlet
                ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
                dispatcherServlet.setApplicationContext(this); //SpringContainer Object를 dispatcherServlet은 매개인자로 가지고 있어야 하기 때문에!

                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispathcerServlet", dispatcherServlet).addMapping("/*");
                });
                webServer.start();

            }
        };
//        applicationContext.registerBean(HelloController.class); // Bean 등록
//        applicationContext.registerBean(SimpleHelloService.class); // Bean 등록

        applicationContext.register(Servlet을Bean등록하기.class);
        //DI는 어떻게...??? ==> 생성자 호출시, 타입을 확인하고 Container의 등록정보를 뒤져서 있으면 던져준다
        applicationContext.refresh(); // Container 초기화




    }
}

/**
 * dispatcherServlet을 만들고 Servlet으로 등록할 때 Spring Container의 정보를 넘겨줘야 한다.
 * 하지만, dispatcherServlet.setApplicationContext(this); Spring Container를 넘겨주는 이 부분이 빠져도 상관없음 (해줌)
 * 왜....???
 * 
 * SpringContainer가 자체적으로 DispatcherServlet은 ApplicationContext가 필요하구나 하고 주입해줌( 혹시 DI방식과 같은 방식이 아닌지 생각중)
 * ApplicationContextAware <-Aware   : 이걸 구현하면 DI해준다
 *
 * 현재 ApplicationContext가 만들어지는 도중에, Bean등록이 이미 됐어야 한다. -> Spring이 관리하는 클래스들 모두 들여다보고 ComponentScan하는 동작을 해야
 * dispatcher Servlet의 인자로 application Context를 주입할 수 있다. -> Application Context는 아직 생성도 안됐는데 주입한다는건 말이 안된다.
 * onrefresh()가 언제 동작하는지, Application Context의 LifeCycle을 공부 할 필요성을 느낌
 */
