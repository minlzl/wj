package com.lzl.wj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.lzl.wj.dao")
@SpringBootApplication
public class WjApplication {

    public static void main(String[] args) {
        SpringApplication.run(WjApplication.class, args);
    }

}
