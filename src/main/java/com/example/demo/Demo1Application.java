package com.example.demo;

import com.example.demo.Server.*;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@ComponentScan
public class Demo1Application {
	@Bean
	public ServletWebServerFactory servletWebServerFactory() {
		System.out.println("메인의 Servlet생성자입니다.");
		return new TomcatServletWebServerFactory();
	}

	@Bean
	public DispatcherServlet dispatcherServlet() {
		return new DispatcherServlet();
	}

	public static void main(String[] args) {
//		Server server = new 서블릿띄우기();
//		Server server = new 프론트컨트롤러();
//		Server server = new 스프링컨테이너연결();
//		Server server = new DispatcherServlet사용();
//		Server server = new SpringServlet통합();
//		Server server = new Bean생성방법ComponentScan();
		최종버전.run(Demo1Application.class,args);

//		발표자료_ServletCotanier가필요할까.run();
//		server.run();
	}
}

