package com.yuuki.cooky;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableCaching
@EnableTransactionManagement
@MapperScan("com.yuuki.cooky.*.dao")
public class CookyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CookyApplication.class, args);
    }


}
