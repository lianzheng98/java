package com.pg.cc.sb.web.controller;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/12/14 6:46 下午
 */

import com.pg.cc.sb.core.util.JSONUtils;
import com.pg.cc.sb.web.annoation.Log2Center;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/app")
@Slf4j
public class TestController {

    @RequestMapping("/test")
    @ResponseBody
    @Log2Center(method = "test")
    public String testDemo() {
        String springframework = "213";
        return springframework;
    }


    @Data
    public static class TestValidationCls {

        @Min(1)
        @NotNull(message = "用户id不能为空")
        int id;

        @NotBlank(message = "name 不能为null")
        String name;

    }

    @PostMapping("/e1")
    public String e1(@RequestBody @Validated TestValidationCls testValidationCls) {
        log.info(JSONUtils.writeValue(testValidationCls));
        return JSONUtils.writeValue(testValidationCls);
    }

}
