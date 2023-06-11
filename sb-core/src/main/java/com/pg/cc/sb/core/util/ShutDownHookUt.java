package com.pg.cc.sb.core.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class ShutDownHookUt {
    @Test
    public void testRuntimeShutDownHook() {
        // 运行时后置钩子
        Runtime.getRuntime().addShutdownHook(new Thread(
                () -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("shutdown hook");
                }
        ));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("now sleep over");
    }
}
