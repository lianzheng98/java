package com.cc.pg.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author lianzheng
 */
@Service
public class AService {
    @Autowired
    private BService bService;


    @Value("${v1Str}")
    private String v1Str;


    public String getS() {
        return bService.add("1");
    }

    public String getSV() {
        return v1Str;
    }

}