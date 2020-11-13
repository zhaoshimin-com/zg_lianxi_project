package com.example.zg.zglianxi01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@MapperScan(basePackages = "com.example.zg.zglianxi01.mapper")
public class Zglianxi01Application {

    public static void main(String[] args) {
        SpringApplication.run(Zglianxi01Application.class, args);
    }

    //先在controller这引入RestTemplate
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
