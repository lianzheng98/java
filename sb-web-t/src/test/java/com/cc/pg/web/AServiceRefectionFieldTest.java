/**
 * @(#)AServiceTest.java, 8月 24, 2023.
 * <p>
 * Copyright 2023 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cc.pg.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.when;

/**
 * @author lianzheng
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(
        classes = {AService.class}
)
public class AServiceRefectionFieldTest {
    @MockBean
    private BService bService;

    @Before
    public void setUp() throws Exception {
        when(bService.add("1")).thenReturn("bServiceMock");
        ReflectionTestUtils.setField(aService, "v1Str", "refectStr");
    }

    @Autowired
    private AService aService;

    @Test
    public void func1() {
        String s = aService.getS();
        System.out.println(s);
    }

    @Test
    public void func2() {
        String s = aService.getSV();
        System.out.println(s);
    }
}