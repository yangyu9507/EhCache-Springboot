package com.yaron.ehcachespringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class EhCacheSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(EhCacheSpringbootApplication.class, args);
    }

}
