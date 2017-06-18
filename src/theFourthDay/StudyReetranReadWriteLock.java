package theFourthDay;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by zhengjiqi on 2017/6/18.
 */
public class StudyReetranReadWriteLock {

    /**
     * 介绍了另外一种锁的机制，现在我们介绍第二种锁读写锁，顾名思义就是隔离读写操作，避免数据在读的过程被修改，出现脏读等线程
     * 问题，读写锁的用法和lock锁差不多，只是声明的时候不同，需要选择具体的锁,现在来让我们了解下
     */

    public static void main(String[] args) {
        /**
         * 介绍第一种情况，写写操作，
         */
        Service6 service6 = new Service6();
//        ThreadWriteLock threadWriteAndWirte=new ThreadWriteLock(service6);
//        ThreadWriteLock threadWriteAndWirte1=new ThreadWriteLock(service6);
//        threadWriteAndWirte.start();
//        threadWriteAndWirte1.start();
        //运行结果可以发现，发生互斥的情况，第二个写锁需要等待第一个写锁释放锁才可以去抢，这个同步的情况是一样的

        /**
         * 第二种情况，读读操作，
         */
//        ThreadReadLcok threadReadLcok = new ThreadReadLcok(service6);
//        ThreadReadLcok threadReadLcok1 = new ThreadReadLcok(service6);
//        threadReadLcok.start();
//        threadReadLcok1.start();
        //执行结果发现，读读操作情况下，并没有出现同步的情况，而是异步执行，说明读读操作的时候，并不会进行锁定


        /**
         * 第三种情况，读写操作
         */

//        ThreadReadLcok threadReadLcok=new ThreadReadLcok(service6);
//        ThreadWriteLock threadWriteLock=new ThreadWriteLock(service6);
//        threadReadLcok.start();
//        try {
//            Thread.sleep(50);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        threadWriteLock.start();
        //执行结果可以发现，在读写操作的情况，出现了互斥的情况，需要等待读锁执行完成后，才可以继续执行。

        /**
         * 第四种情况，写读操作
         */

        ThreadReadLcok threadReadLcok = new ThreadReadLcok(service6);
        ThreadWriteLock threadWriteLock = new ThreadWriteLock(service6);
        threadWriteLock.start();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadReadLcok.start();

        //执行结果可以发现，在写读操作中，也出现了互斥的情况，必须等待写操作才可以继续执行.


        //总结，只有执行读读操作不会进行互斥的操作外，其他都会出现相应的互斥同步情况，也是通过这种形式来保证线程读写安全性
    }
}

/**
 * 构建一个服务类
 */

class Service6 {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void write() {
        lock.writeLock().lock();//锁定写锁
        System.out.println(Thread.currentThread().getName() + "进入写锁");
        try {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + "释放写锁");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();//释放写锁
        }
    }

    public void read() {
        lock.readLock().lock();//锁定读锁
        System.out.println(Thread.currentThread().getName() + "进入读锁");
        try {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + "释放读锁");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();//释放读锁
        }
    }
}

class ThreadWriteLock extends Thread {
    private Service6 service6;

    public ThreadWriteLock(Service6 service6) {
        super();
        this.service6 = service6;
    }

    @Override
    public void run() {
        service6.write();
    }
}

class ThreadReadLcok extends Thread {
    private Service6 service6;

    public ThreadReadLcok(Service6 service6) {
        super();
        this.service6 = service6;
    }

    @Override
    public void run() {
        service6.read();
    }
}