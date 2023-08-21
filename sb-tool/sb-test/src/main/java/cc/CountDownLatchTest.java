package cc;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            new Thread(new MyThread(latch), "player" + i).start();
        }

        latch.await();

    }

    private static class MyThread implements Runnable {
        private CountDownLatch latch;

        public MyThread(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            // 开始准备
            latch.countDown();
        }
    }
}
