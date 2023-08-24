package com.cc.pg.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lianzheng
 */

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = SpringBootdemoApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebBootTest {

    @LocalServerPort
    private int rdmServerPort;


    @Test
    public void ff() {
        System.out.println(
                rdmServerPort
        );
    }
}