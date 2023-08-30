package com.cyclingTour;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
@MapperScan("com.cyclingTour.mapper")
public class JavaCyclingTourApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaCyclingTourApplication.class, args);

        System.out.println("Application is running! Access URL: " + "http://47.111.26.194:8085/index");

    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

