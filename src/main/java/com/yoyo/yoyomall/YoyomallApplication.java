package com.yoyo.yoyomall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication()
//@MapperScan("com.yoyo.yoyomall.mapper")
@EnableTransactionManagement//开启事务
public class YoyomallApplication {

    public static void main(String[] args) {
        SpringApplication.run(YoyomallApplication.class, args);

    }

}
