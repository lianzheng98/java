package cc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadRandomWork {
    private static class A {

        private static class Money {
            private int money;

            public Money() {
                money = 1000;
            }

            public int getMoney() {
                return money;
            }

            public void setMoney(int money) {
                this.money = money;
            }
        }

        private static class AccThread extends Thread {
            private List<Money> lists;
            private Map<Money, ReentrantLock> maps;

            public AccThread(List<Money> lists, Map<Money, ReentrantLock> maps) {
                this.lists = lists;
                this.maps = maps;
            }

            public void trylock(int acc1, int acc2) throws InterruptedException {

                boolean flagA = false;
                boolean flagB = false;
                try {

                    flagA = maps.get(acc1).tryLock();
                    flagB = maps.get(acc2).tryLock();
                } finally {
                    if (flagA && flagB) {

                    } else if (flagA) {
                        maps.get(acc1).unlock();
                    } else if (flagB) {
                        maps.get(acc2).unlock();
                    }
                }

                Thread.sleep(100);
            }

            @Override
            public void run() {


                for (int i = 0; i < 100; i++) {
                    int acc1 = new Random().nextInt(10);
                    int acc2 = new Random().nextInt(10);

                    while (acc1 == acc2)
                        acc1 = new Random().nextInt(10);
                    try {
                        trylock(acc1, acc2);
                        if (lists.get(acc1).getMoney() > 10) {
                            lists.get(acc1).setMoney(1);
                            lists.get(acc2).setMoney(1);
                        }
                    } catch (InterruptedException e) {

                    } finally {
                        maps.get(acc1).unlock();
                        maps.get(acc2).unlock();
                    }
                }
            }
        }

        public static void main(String[] args) {
            List<Money> lists = new ArrayList<>();
            for (int i = 0; i < 20; i++) lists.add(new Money());
            Map<Money, ReentrantLock> maps = new HashMap<>();
            for (int i = 0; i < 20; i++)
                maps.put(lists.get(i), new ReentrantLock());
            List<Thread> threadList = new ArrayList<Thread>();
            for (int i = 0; i < 20; i++) {
                threadList.add(new AccThread(lists, maps));
            }
            for (int i = 0; i < 20; i++) {
                try {
                    threadList.get(i).join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i = 0; i < 20; i++) {
                System.out.println(lists.get(i).getMoney());
            }
        }
    }

    public static void main(String[] args) {
        List<Account> accountList = new ArrayList<Account>();
        Map<Account, ReentrantLock> accLock = new HashMap<Account, ReentrantLock>();

        //10个账户;
        Account acc = null;
        for (int i = 0; i < 10; i++) {
            acc = new Account(String.valueOf(i + 1));
            accountList.add(acc);
            accLock.put(acc, new ReentrantLock());
        }

        //10个线程;
        List<Thread> threadList = new ArrayList<Thread>();
        for (int i = 0; i < 10; i++) {
            threadList.add(new AccountThread(accountList, accLock));
        }

        for (int i = 0; i < 10; i++) {
            threadList.get(i).start();
        }

        //等待10个线程运行结束;
        for (int i = 0; i < 10; i++) {
            try {
                threadList.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int total = 0;
        for (int i = 0; i < 10; i++) {
            System.out.println("第" + (i + 1) + "个账户" + accountList.get(i));
            total += accountList.get(i).getMoney();
        }
        System.out.println("总金额：" + total);
    }

    public static class AccountThread extends Thread {

        //存储10个账户;
        private ArrayList<Account> accList = null;

        //每个账户对应一个锁，使得一个线程仅锁当前要操作的两个账户对应的锁;
        Map<Account, ReentrantLock> accLock = null;
        Random random = new Random();
        int acc1 = 0;
        int acc2 = 0;
        int money = 0;

        public AccountThread(List<Account> account, Map<Account, ReentrantLock> accLock) {
            this.accList = (ArrayList<Account>) account;
            this.accLock = accLock;
        }

        private void acquireLocks(Lock lockA, Lock lockB) throws InterruptedException {
            while (true) {
                boolean bLockA = false;
                boolean bLockB = false;
                try {
                    bLockA = lockA.tryLock();
                    bLockB = lockB.tryLock();
                } finally {
                    if (bLockA && bLockB)
                        return;
                    else if (bLockA)
                        lockA.unlock();
                    else if (bLockB)
                        lockB.unlock();
                }
                Thread.sleep(1);
            }
        }

        public void run() {

            for (int i = 0; i < 100; i++) {
                acc1 = random.nextInt(10);
                acc2 = random.nextInt(10);
                //禁止相同账户相互转账;
                while (acc1 == acc2) {
                    acc2 = random.nextInt(10);
                }
                money = random.nextInt(1000);

                try {
                    acquireLocks(accLock.get(accList.get(acc1)), accLock.get(accList.get(acc2)));
                    if (accList.get(acc1).getMoney() > money) {
                        accList.get(acc1).drawMoney(money);
                        accList.get(acc2).saveMoney(money);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    accLock.get(accList.get(acc1)).unlock();
                    accLock.get(accList.get(acc2)).unlock();
                }
            }
        }
    }

    public static class Account {
        private String name = "";
        private int money = 10000;

        public Account(String name) {
            this.name = name;
        }

        public void saveMoney(int money) {
            this.money += money;
        }

        public void drawMoney(int money) {
            this.money -= money;
        }

        public int getMoney() {
            return money;
        }

        public String toString() {
            return String.valueOf(money);
        }

    }
}