package com.pg.cc.sb.core.future;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorCom {
    private static final int POOL_SIZE = 12;

    private static final ExecutorService thumbnailExecutorService = new ThreadPoolExecutor(POOL_SIZE, POOL_SIZE,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>()
    );

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Item {
        int input;
        int output;
    }

    @Test
    public void testRunTaskByExecutorCompletionService() {
        try {
            runTaskByExecutorCompletionService();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * use ExecutorCompletionService to run task
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private static void runTaskByExecutorCompletionService() throws InterruptedException, ExecutionException {
        CompletionService<Item> completionService = new ExecutorCompletionService<>(thumbnailExecutorService);
        int TIMES = 10;
        for (int i = 0; i < TIMES; i++) {

            int finalI = i;
            completionService.submit(() -> {
                System.out.println("当前的输入是 " + finalI);
                Thread.sleep(1000);
                return new Item(finalI, finalI * 2);
            });
        }

        for (int i = 0; i < TIMES; i++) {
            long maxWaitSeconds = 1;
            Future<Item> taskResultFuture = completionService.poll(maxWaitSeconds, TimeUnit.SECONDS);
            if (taskResultFuture == null) {
                System.out.println("执行异常");
            } else {
                System.out.println("线程池执行结果 " + taskResultFuture.get());
            }
        }

    }
}
