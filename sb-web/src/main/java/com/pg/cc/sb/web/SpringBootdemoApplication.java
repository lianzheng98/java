package com.pg.cc.sb.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/12/14 6:45 下午
 */
@SpringBootApplication
@EnableScheduling
public class SpringBootdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootdemoApplication.class, args);
    }
}
