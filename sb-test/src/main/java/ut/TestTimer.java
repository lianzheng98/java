package ut;

import org.junit.Test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TestTimer {

    @Test
    public void testTimer() {
        // 定时任务
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // 定时任务的代码
                System.out.println("定时任务执行了： " + new Date());
            }
        };
        Timer timer = new Timer();
        long delay = 0L;
        long intervalPeriod = 3000L;
        // 延迟2秒后开始执行任务，每3秒执行一次
        timer.scheduleAtFixedRate(task, delay, intervalPeriod);

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println('1');
        }


    }


    class MyThread extends Thread {
        public void run() {
            // 这里产生了一个异常
            int[] nums = new int[5];
            nums[10] = 10;
        }
    }

    @Test
    public void testExceptionHandler() throws InterruptedException {
        MyThread t = new MyThread();
        // 为线程 t 指定异常处理器
        t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("线程 " + t.getName() + " 出现了异常：");
                e.printStackTrace();
            }
        });
        t.start();
        System.out.println("123");

        Thread.sleep(1000);
        System.out.println("456");
    }

}
