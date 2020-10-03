package com.ry.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@EnableFeignClients(basePackages = {"com.sibecommon.feign"})
@SpringBootApplication
@MapperScan({"com.ry.manage.sys.mapper","com.sibecommon.repository.mapper"})
@ComponentScan({"com.ry.manage", "com.sibecommon"})
public class ManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageApplication.class, args);
    }

}
