package theFourthDay;

import com.sun.org.apache.xml.internal.utils.ThreadControllerWrapper;

import java.util.Date;
import java.util.concurrent.TimeUnit;
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
//        service5.currentThead();


        //第九个api是isLock（），该api是判断当前锁是否处于锁定状态.相对于之前的api，该api是判断锁的情况，而不是线程的情况
//        ThreadIsLocked threadIsLocked=new ThreadIsLocked(service5);
//        threadIsLocked.start();
//        try{
//            Thread.sleep(50);
//            System.out.println("锁是否被锁定："+service5.lock.isLocked());
//        }catch(InterruptedException e){
//            e.printStackTrace();
//        }s


        //第十个api是lockInterruptibly(),该api是用来获取锁的，作用是如果当前线程没有处于中断情况下则获取锁，如果处于中断，则报出异常
//        ThreadLockInterruptibly lockInterruptibly = new ThreadLockInterruptibly(service5);
//        ThreadLockInterruptibly lockInterruptibly1 = new ThreadLockInterruptibly(service5);
//        lockInterruptibly.start();
//        lockInterruptibly1.start();
//        lockInterruptibly1.interrupt();

        //第十一和十二两个api分别是tryLock（），tryLLock（long timeOut，TimeUnit timeUnit），两个api作用相似，第一个是如果
        //锁是空余，则获取锁，第二个是在时间内，锁是空余的，就获取锁,两者相同，后者可以限定时间
//        ThreadTryLock threadTryLock = new ThreadTryLock(service5);
//        ThreadTryLock threadTryLock1 = new ThreadTryLock(service5);
//        threadTryLock.start();
//        threadTryLock1.start();

        //第十三个api是awaitUninterruptibly（），该api是等待不可以中断的线程，就是使用了中断后，对其无效，不会抛出异常
//        ThreadAwaitInterrupt threadAwaitInterrupt=new ThreadAwaitInterrupt(service5);
//        threadAwaitInterrupt.start();
//        threadAwaitInterrupt.interrupt();//没有抛出异常
//        service5.signal();//在等待状态没有进行中断，但是中断操作会等待等待状态结束出现

        //最后一个api是awaitUntil(Data dataLine),这个api是等待到最后期限是会自动唤醒，和await不同的是，这个是传入一个时间点，await是
        //传入一个时间段
        ThreadAwaitUnite threadAwaitUnite=new ThreadAwaitUnite(service5);
        threadAwaitUnite.start();

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

    public void queueCount() {
        lock.lock();
        try {
            //System.out.println("进入线程");
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void waitQueueCount() {
        lock.lock();
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void waitQueueCountTest() {
        lock.lock();
        try {
            System.out.println(lock.getWaitQueueLength(condition));
        } finally {
            lock.unlock();
        }

    }

    public void signal(){
        lock.lock();
        condition.signalAll();
        lock.unlock();
    }
    public void waitQueueTest() {
        lock.lock();
        try {
            System.out.println(lock.hasWaiters(condition));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void currentThead() {
        System.out.println(lock.isHeldByCurrentThread());
        lock.lock();
        try {
            System.out.println(lock.isHeldByCurrentThread());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void isLock() {
        System.out.println(Thread.currentThread().getName() + "是否锁定：" + lock.isLocked());
        lock.lock();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void lockInterruptibly() {
        try {
            System.out.println("线程:" + Thread.currentThread().getName() + "是否中断" + Thread.currentThread().isInterrupted());
            lock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName() + "获取锁");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "出现异常");
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void tryLock() {
        try {
            if (lock.tryLock(1, TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getName()+"获取锁成功");
                Thread.sleep(2000);
            } else System.out.println(Thread.currentThread().getName()+"获取锁失败");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void awaitLockinterrupt(){
        try{
            lock.tryLock();
            System.out.println("进入等待状态");
            condition.awaitUninterruptibly();
            System.out.println("等待结束");
        }catch (Exception e){

            e.printStackTrace();
        }finally {
            System.out.println(Thread.currentThread().isInterrupted());
            lock.unlock();
        }
    }

    public void awaitUnite(){
        try{
            lock.lock();
            System.out.println("进入等待");
            condition.awaitUntil(new Date(System.currentTimeMillis()+2000));
            System.out.println("等待唤醒");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
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

class ThreadIsLocked extends Thread {
    private Service5 service5;

    public ThreadIsLocked(Service5 service5) {
        super();
        this.service5 = service5;
    }

    @Override
    public void run() {
        service5.isLock();
    }
}

class ThreadLockInterruptibly extends Thread {
    private Service5 service5;

    public ThreadLockInterruptibly(Service5 service5) {
        super();
        this.service5 = service5;
    }

    @Override
    public void run() {
        service5.lockInterruptibly();
    }
}

class ThreadTryLock extends Thread {
    private Service5 service5;

    public ThreadTryLock(Service5 service5) {
        super();
        this.service5 = service5;
    }

    @Override
    public void run() {
        service5.tryLock();
    }
}

class ThreadAwaitInterrupt extends Thread{
    private Service5 service5;
    public ThreadAwaitInterrupt(Service5 service5){
        super();
        this.service5=service5;
    }

    @Override
    public void run(){
        service5.awaitLockinterrupt();
    }
}

class ThreadAwaitUnite extends Thread{
    private Service5 service5;
    public ThreadAwaitUnite(Service5 service5){
        super();
        this.service5=service5;
    }

    @Override
    public void run(){
        service5.awaitUnite();
    }
}
