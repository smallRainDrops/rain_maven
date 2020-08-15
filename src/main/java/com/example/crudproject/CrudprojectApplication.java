package com.example.crudproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 王政委读作
 */
@SpringBootApplication
@MapperScan("com.example.crudproject.dao")
public class CrudprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudprojectApplication.class, args);
    }

}
