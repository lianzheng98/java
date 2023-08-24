package com.cc.pg.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lianzheng
 */
@RestController
@RequestMapping("/a")
public class AController {

    @GetMapping("/s")
    public String getS() {
        return "s";
    }

}