package com.sadtrain.autotrans;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@MapperScan("com.sadtrain.autotrans.db.mapper")
@SpringBootApplication
public class AutoTransApplication {

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        System.setProperty("console.encoding", "UTF-8");
        String property = System.getProperty("os.name");
        if (property.contains("Windows")) {
            System.setProperty("osbriefname", "windows");
        } else if (property.contains("Mac")) {
            System.setProperty("osbriefname", "mac");
        } else {
            System.setProperty("osbriefname", "default");
        }
        SpringApplication.run(AutoTransApplication.class, args);
    }
}
