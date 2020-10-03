package com.ry.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@EnableFeignClients(basePackages = { "comm.feign"})
@SpringBootApplication
@MapperScan({"com.ry.manage.sys.mapper","comm.repository.mapper"})
//@ComponentScan({"com.ry.manage","comm.utils.redis.impl","comm.config","comm.runner"
//,"comm.repository.mapper"})
@ComponentScan({"com.ry.manage","comm.utils","comm.config","comm.runner"
        ,"comm.repository.mapper","comm.service"})
public class ManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageApplication.class, args);
    }

}
