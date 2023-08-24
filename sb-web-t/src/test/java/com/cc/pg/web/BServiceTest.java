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
@ContextConfiguration(
        classes = {BService.class}
)
public class BServiceTest {

    @Autowired
    private BService bService;

    @Test
    public void func1() {
        String addStr = bService.add("1");
        System.out.println(
                addStr
        );
    }
}