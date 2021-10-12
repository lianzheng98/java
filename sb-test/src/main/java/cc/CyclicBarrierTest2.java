package cc;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 多线程 计算 1+2+3+4+......+n
 *
 * @author ZeWe
 */
public class CyclicBarrierTest2 {

    private static Integer sum = 0;
    private static Integer n = 10005;

    private static Integer cyclicSize = 10; //线程数量
    private static CyclicBarrier cyclicBarrier;
    private static Lock lock;

    public static void main(String[] args) {
        cyclicBarrier = new CyclicBarrier(cyclicSize, new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "所有子线程相加完毕，准备加和 sum--> sum:" + sum);
            }
        });

        lock = new ReentrantLock();

        int avg = n / cyclicSize;
        int rem = n % cyclicSize;
        int left, right;
        for (int i = 1; i <= cyclicSize; i++) {
            left = (i - 1) * avg + 1;
            right = i == cyclicSize ? (i * avg + rem) : (i * avg);
            new Thread(new Run(left, right)).start();
        }


    }

    static class Run implements Runnable {

        private int left;
        private int right;

        public Run(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public void run() {
            int res = 0;
            for (int i = left; i <= right; i++) {
                res += i;
            }
            System.out.println(Thread.currentThread().getName() + " 等待其他线程 --> left:" + left + ", right:" + right + ", res:" + res);
            try {
                cyclicBarrier.await(); //等待其他线程执行
            } catch (Exception e) {
                e.printStackTrace();
            }

            lock.lock();
            try {
                sum += res;
                System.out.println(Thread.currentThread().getName() + " 添加至sum --> left:" + left + ", right:" + right + ", res:" + res + ", sum: " + sum);
            } finally {
                lock.unlock();
            }

        }

    }

}