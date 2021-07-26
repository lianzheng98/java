package com.pg.cc.sb.web.crane;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/12/14 6:56 下午
 */
@Service
@Slf4j
public class AckScheduler {

    int i = 0;

    @Scheduled(cron = "0/1 * *  * * ? ")
    public String CraneTest() throws Exception {
        System.out.println("123");
        log.info("123 {}", i);
        ++i;
        if (i % 3 == 0) {
            throw new Exception("123");
        }
        return String.valueOf(System.currentTimeMillis());
    }
}
