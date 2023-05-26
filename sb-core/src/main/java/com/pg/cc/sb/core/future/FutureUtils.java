package com.pg.cc.sb.core.future;

import com.pg.cc.sb.core.util.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class FutureUtils {
    public static <T> T CompletableGetWithoutException(CompletableFuture<T> x) {
        try {
            return x.get();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return null;
    }

    @Test
    public void testRunCompletableFuture() {
        // generate Task
        List<CompletableFuture<String>> taskList = IntStream.range(0, 10)
                .boxed()
                .map(i -> CompletableFuture.supplyAsync(() -> String.valueOf(i)))
                .collect(Collectors.toList());

        //wait task finish
        CompletableFuture.allOf(taskList.toArray(new CompletableFuture<?>[0])).join();

        // 获取任务参数
        taskList.stream().forEach(x -> x.whenComplete((r, e) -> {
            if (e == null) {
                log.info(r);
            }
        }));

        List<String> results = taskList.stream().map(FutureUtils::CompletableGetWithoutException).filter(Objects::nonNull).collect(Collectors.toList());
        log.info(JSONUtils.writeValue(results));
    }


    @Test
    public void testResponsibilityChain() {
        CompletableFuture.supplyAsync(() -> {
            log.info("Task1: 执行耗时的任务...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello";
        }).thenApplyAsync(result -> {
            log.info("Task2: 对结果进行转换...");
            return result + " world";
        }).thenAcceptAsync(result -> {
            log.info("Task3: " + result);
        }).join();
    }

    @Test
    public void testFuncResponsibilityChain() {
        Supplier<String> supplierFunc1 = () -> "Hello";
        CompletableFuture<String> future = CompletableFuture.supplyAsync(supplierFunc1);

        BiConsumer<String, Throwable> biLogFunc = (r, e) -> {
            if (e == null) {
                log.info("正常结果：{}", r);
            }
        };
        future.whenCompleteAsync(biLogFunc);

        Function<String, CompletionStage<String>> function2 = (x) -> CompletableFuture.supplyAsync(() -> x + " is a boy");
        CompletableFuture<String> future2 = future.thenCompose(function2);

        Function<String, CompletionStage<Void>> function3 = (x) -> CompletableFuture.runAsync(() -> {
            log.info(x);
        });

        CompletableFuture<Void> future3 = future2.thenCompose(function3);
        future3.join();
    }

}
