package com.cc.pg.web;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(
        classes = {AService.class}
)
@SpringBootTest
public class AServiceBootTest {

    @MockBean
    private BService bService;

    @Autowired
    private AService aService;

    @Test
    public void func1() {
        String sv = aService.getSV();
        System.out.println(sv);
    }
}