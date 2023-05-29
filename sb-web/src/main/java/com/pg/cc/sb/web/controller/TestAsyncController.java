package com.pg.cc.sb.web.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
public class TestAsyncController {

    @GetMapping("/testAsyncController")
    @ResponseBody
    @SneakyThrows
    public List<String> testAsyncController() {
        long now = System.currentTimeMillis();
        log.info("now {}", now);
        CompletableFuture<String> stringCompletableFuture = task1();
        CompletableFuture<String> stringCompletableFuture1 = task2();
        CompletableFuture<String> stringCompletableFuture2 = task3();
        CompletableFuture.allOf(stringCompletableFuture, stringCompletableFuture1, stringCompletableFuture2).join();
        log.info("cost {}", System.currentTimeMillis() - now);
        return Arrays.asList(
                stringCompletableFuture.get(),
                stringCompletableFuture1.get(),
                stringCompletableFuture2.get()
        );
    }


    @Async("threadPoolTaskExecutor")
    public CompletableFuture<String> task1() {
        try {
            log.info(" wait ");
            Thread.sleep(1000);
            log.info(" wait over ");
            return CompletableFuture.completedFuture("1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<String> task2() {
        try {
            log.info(" wait ");
            Thread.sleep(1000);
            log.info(" wait over ");
            return CompletableFuture.completedFuture("2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Async("threadPoolTaskExecutor")
    public CompletableFuture<String> task3() {
        try {
            log.info(" wait ");
            Thread.sleep(1000);
            log.info(" wait over ");
            return CompletableFuture.completedFuture("3");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}