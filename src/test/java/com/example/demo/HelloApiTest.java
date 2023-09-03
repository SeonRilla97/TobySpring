package com.example.demo;


import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HelloApiTest {

    @Test
    void HelloApi() {
        //http localhost:8080/hello?name=Spring
        //RestTemplate(OK는 괜찮지만, 404같은건 예외를 던져버림

        TestRestTemplate rest = new TestRestTemplate();

        ResponseEntity<String> res =
                rest.getForEntity("http://localhost:8080/hello?name={name}", String.class, "Spring");

        //status 200
        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        //header(content-type) text/plain
        Assertions.assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
        //body hello Spring
        Assertions.assertThat(res.getBody()).isEqualTo("*Hello Spring*");


    }


    @Test
    void failsHelloApi() {
        //http localhost:8080/hello?name=Spring
        //RestTemplate(OK는 괜찮지만, 404같은건 예외를 던져버림

        TestRestTemplate rest = new TestRestTemplate();

        ResponseEntity<String> res =
                rest.getForEntity("http://localhost:8080/hello?name={name}", String.class);

        //status 200
        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        //header(content-type) text/plain



    }

}
