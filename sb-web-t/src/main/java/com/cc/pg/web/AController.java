/**
 * @(#)Acontroller.java, 8月 24, 2023.
 * <p>
 * Copyright 2023 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
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