package com.example.demo;

import com.example.demo.controller.HelloController;
import com.example.demo.service.HelloService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 단위 테스트
 * 어느 컨테이너에도 의존하지 않고 테스트를 진행
 * -> 테스트 속도가 매우 빠르다
 */
public class HelloControllerTest {
    @Test
    void HelloController() { //성공 케이스

        HelloController helloController = new HelloController(name -> name);

        String ret = helloController.hello("Test");

        Assertions.assertThat(ret).isEqualTo("Test");
    }


    @Test
    void failsHelloController() { //실패 케이스
        HelloController helloController = new HelloController(name -> name);
        Assertions.assertThatThrownBy(()->{
            helloController.hello(null);
        }).isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(()->{
            helloController.hello("");
        }).isInstanceOf(IllegalArgumentException.class);
    }


}
