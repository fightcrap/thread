package theFourthDay;

import com.sun.org.apache.xml.internal.utils.ThreadControllerWrapper;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhengjiqi on 2017/6/15.
 */
public class StudyLockApi {
    //介绍下lock常用的几种api，有了api才可以方便我们更好的去管理

    /**
     * 在这边我会介绍14个常见的api，
     */

    public static void main(String[] args) {
        //第一个api是getHoldCount（），这个api是获取当前线程锁定锁的个数，也就是调用类lock方法的次数
        Service5 service5 = new Service5();
//        Threads threads = new Threads(service5);
//        threads.start();

        //第二个api是getQueueCount（），这个api是获取线程就绪等待获取锁的个数,启动线程这边用了一个延时语句，保证全部线程启动


//        for(int i=0;i<10;i++){
//            ThreadQueueCount threads = new ThreadQueueCount(service5);
//            System.out.println(i);
//            threads.start();
//        }
//        try{
//            Thread.sleep(50);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
//        System.out.println(service5.lock.getQueueLength());


        //第三个api是getWaitQueueCount（），这个api是获取线程等待的线程个数，也就是wait状态的线程个数。但是前提是这个语句需要在lock的情况下才可以
        //不然会报异常

//        for(int i=0;i<10;i++){
//            ThreadwaitQueueCount threads = new ThreadwaitQueueCount(service5);
//            //System.out.println(i);
//            threads.start();
//        }
//        try{
//            Thread.sleep(50);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
//        service5.waitQueueCountTest();
        //System.out.println(service5.lock.getWaitQueueLength(service5.condition));  会出现异常


        //第四个api是hasQueueThread(),这个api是获取指定线程是否是就绪等待获取该锁
//        ThreadQueueCount[] threadQueueCount=new ThreadQueueCount[2];
//        for(int i=0;i<2;i++){
//            threadQueueCount[i]=new ThreadQueueCount(service5);
//            threadQueueCount[i].start();
//        }
//        try{
//            Thread.sleep(50);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
//        System.out.println(service5.lock.hasQueuedThread(threadQueueCount[1]));



        //第五个api是hasQueueThreads（），这个api是获取该锁是否有线程进行等待.和之前的的api相比，只是这个并不是判断特定的
//        ThreadQueueCount[] threadQueueCount=new ThreadQueueCount[2];
//        for(int i=0;i<2;i++){
//            threadQueueCount[i]=new ThreadQueueCount(service5);
//            threadQueueCount[i].start();
//        }
//        try{
//            Thread.sleep(50);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
//        System.out.println(service5.lock.hasQueuedThreads());
//

        //第六个api是hasWaiters（），这个是api是用来判断该锁有没有线程处于阻塞等待的情况,这个需要在lock的环境情况下  不然会出现异常的
//        ThreadwaitQueueCount[] threadQueueCount=new ThreadwaitQueueCount[2];
//        for(int i=0;i<2;i++){
//            threadQueueCount[i]=new ThreadwaitQueueCount(service5);
//            threadQueueCount[i].start();
//        }
//        try{
//            Thread.sleep(50);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
//        service5.waitQueueTest();
        //System.out.println(service5.lock.hasWaiters(service5.condition));   出现异常



        //第七个api是isFair（），这个就是用来判断是否是公平锁，这个比较简单  就不演示了



        //第八个api是isHeldByCurrentThead（），该api是判断当前线程是否获取到锁。
        service5.currentThead();


        //第九个api是isLock（），该api是判断当前锁是否处于锁定状态.
    }
}

/**
 * 构建一个服务类
 */
class Service5 {
    public ReentrantLock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();

    public void method() {
        lock.lock();
        try {
            System.out.println(lock.getHoldCount());
            method1();
        } finally {
            lock.unlock();
        }
    }

    public void method1() {
        lock.lock();
        try {
            System.out.println(lock.getHoldCount());

        } finally {
            lock.unlock();
        }
    }

    public void queueCount(){
        lock.lock();
        try{
            //System.out.println("进入线程");
            Thread.sleep(Integer.MAX_VALUE);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
    public void waitQueueCount(){
        lock.lock();
        try{
            condition.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void waitQueueCountTest(){
        lock.lock();
        try{
            System.out.println(lock.getWaitQueueLength(condition));
        }finally {
            lock.unlock();
        }

    }

    public void waitQueueTest(){
        lock.lock();
        try{
            System.out.println(lock.hasWaiters(condition));
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }

    }

    public void currentThead(){
        System.out.println(lock.isHeldByCurrentThread());
        lock.lock();
        try{
            System.out.println(lock.isHeldByCurrentThread());
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }

    }
}

class Threads extends Thread {
    private Service5 service5;

    public Threads(Service5 service5) {
        super();
        this.service5 = service5;
    }

    @Override
    public void run() {
        service5.method();
    }
}

class ThreadQueueCount extends Thread {
    private Service5 service5;

    public ThreadQueueCount(Service5 service5) {
        super();
        this.service5 = service5;
    }

    @Override
    public void run() {
        service5.queueCount();
    }
}

class ThreadwaitQueueCount extends Thread {
    private Service5 service5;

    public ThreadwaitQueueCount(Service5 service5) {
        super();
        this.service5 = service5;
    }

    @Override
    public void run() {
        service5.waitQueueCount();
    }
}

