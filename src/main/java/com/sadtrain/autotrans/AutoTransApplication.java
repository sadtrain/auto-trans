package com.sadtrain.autotrans;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@MapperScan("com.sadtrain.autotrans.db.mapper")
@SpringBootApplication
public class AutoTransApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoTransApplication.class, args);
    }
}
