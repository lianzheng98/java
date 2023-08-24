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