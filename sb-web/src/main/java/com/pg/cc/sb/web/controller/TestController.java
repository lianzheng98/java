package com.pg.cc.sb.web.controller;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/12/14 6:46 下午
 */

import com.pg.cc.sb.web.annoation.Log2Center;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/app")
public class TestController {

    @RequestMapping("/test")
    @ResponseBody
    @Log2Center(method = "test")
    public String testDemo() {
        String springframework = "213";
        return springframework;
    }
}
