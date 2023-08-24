/**
 * @(#)AbServiceTest.java, 8月 24, 2023.
 * <p>
 * Copyright 2023 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cc.pg.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
/**
 * @author lianzheng
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AService.class, BService.class})
public class AbServiceTest {
    @Autowired
    private AService aService;
    @Autowired
    private BService bService;

    @Test
    public void func1() {
        String s = aService.getS();
        System.out.println(s);
    }
}