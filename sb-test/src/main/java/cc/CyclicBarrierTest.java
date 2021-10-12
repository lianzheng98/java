package cc;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        for (int i = 0; i < 3; i++) {
            new Thread(new MyRunnable(cyclicBarrier), "队友" + i).start();
        }
        System.out.println("game start");

    }

    private static class MyRunnable implements Runnable {
        private CyclicBarrier cyclicBarrier;

        public MyRunnable(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
        //
            for(int i =0;i<3;i++){
                try {
                    this.cyclicBarrier.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
