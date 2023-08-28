package com.example.demo;

import com.example.demo.Server.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


public class Demo1Application {

	public static void main(String[] args) {
//		Server server = new 서블릿띄우기();
//		Server server = new 프론트컨트롤러();
//		Server server = new 스프링컨테이너연결();
//		Server server = new DispatcherServlet사용();
		Server server = new SpringServlet통합();
		server.run();
	}
}
