package theFourthDay;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhengjiqi on 2017/6/14.
 */
public class StudyReentrantLock {
    /**
     * 能控制线程同步的方式不止synchronized一种形式，而且运用synchronized相对性能较差，现在介绍一种更加灵活，性能较好的方式
     * 那就是lock锁
     */

    public static void main(String[] args) {
//        Service service = new Service();
//        Thread thread = new Thread() {
//            @Override
//            public void run() {
//                service.method();
//            }
//        };
//        Thread thread1 = new Thread() {
//            @Override
//            public void run() {
//                service.method();
//            }
//        };
//        thread.start();
//        thread1.start();
        //可以发现运行结果是课同步是一样的情况，在线程锁定的情况下，其他线程是不能访问的，但是必须lock和unlock联合用，不然锁不会被释放的

        /**
         * lock锁的等待和通知机制，和wait／notify机制有个类似的，但是灵活成度而言比较高，就是Condition类
         */
//         Service2 service2=new Service2();
//         Thread1 thread1=new Thread1(service2);
//         Thread2 thread2=new Thread2(service2);
//         try {
//             thread1.start();
//             Thread.sleep(50);
//             thread2.start();
//         }catch (InterruptedException e){
//             e.printStackTrace();
//         }
         //发现运行结果和wait／notify机制相同，await会立即释放锁，而signal却要等待unlock的释放。那么既然差不多，那么Condition有啥特殊的呢，特殊的地方就在于，
        //Condition可以有组的概念.
        Service3 service3=new Service3();
        Thread3 thread3=new Thread3(service3);
        Thread4 thread4=new Thread4(service3);
        Thread5 thread5=new Thread5(service3);
        Thread6 thread6 =new Thread6(service3);
        thread3.start();
        thread5.start();
        try{
            Thread.sleep(50);
            thread4.start();
            Thread.sleep(2000);
            thread6.start();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        //在运行过程中，我们发现，虽然是用了signalAll（），但是通知不到另外的Condition对象，说明，Condition层现组的形式，只会通知同类的Condition对象

    }
}

/**
 * 构建一个服务类
 */

class Service {
    private Lock lock = new ReentrantLock();

    public void method() {
        try {
            lock.lock();
            System.out.println("线程运行开始：" + System.currentTimeMillis());
            Thread.sleep(2000);
            System.out.println("线程运行结束：" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

/**
 * 构建一个服务类来验证Condition的通知和释放功能
 * 构建两个线程来验证Condition
 */
class Service2 {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void waitInformation() {
        try {
            lock.lock();
            System.out.println("进入等待：" + System.currentTimeMillis());
            condition.await();
            System.out.println("得到通知："+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void notifyInformation(){
try{
    lock.lock();
    System.out.println("开始通知:"+System.currentTimeMillis());
    condition.signal();
    Thread.sleep(1000);
}catch (InterruptedException e){
    e.printStackTrace();
}finally {
    lock.unlock();
}

    }
}

class Thread1 extends Thread{
    private Service2 service2;
    public Thread1(Service2 service2){
        super();
        this.service2=service2;
    }
    @Override
    public void run(){
        service2.waitInformation();
    }
}
class Thread2 extends Thread{
    private Service2 service2;
    public Thread2(Service2 service2){
        super();
        this.service2=service2;
    }
    @Override
    public void run(){
        service2.notifyInformation();
    }
}

/**
 * 构建一个服务类，来实现Condition的组效果，实现顺序执行
 * 并构建4个线程来验证
 */
class Service3{
    private Lock lock=new ReentrantLock();
    private Condition conditionA=lock.newCondition();
    private Condition conditionB=lock.newCondition();
    public void notifyB(){
        try {
            lock.lock();
            System.out.println("通知线程：B" + System.currentTimeMillis());
            Thread.sleep(2000);
            conditionB.signalAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void waitNotifyB(){
        try {
            lock.lock();
            System.out.println("等待线程：B" + System.currentTimeMillis());

            conditionB.await();
            Thread.sleep(2000);
            System.out.println("获取通知线程B："+System.currentTimeMillis());
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
    public void notifyA(){
        try {
            lock.lock();
            System.out.println("通知线程：A" + System.currentTimeMillis());
            Thread.sleep(2000);
            conditionA.signalAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void waitNotifyA(){
        try {
            lock.lock();
            System.out.println("等待线程：A" + System.currentTimeMillis());

            conditionA.await();
            Thread.sleep(2000);
            System.out.println("获取通知线程A："+System.currentTimeMillis());
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
}

class Thread3 extends Thread {
    private Service3 service3;
    public Thread3(Service3 service3){
        super();
        this.service3=service3;
    }
    @Override
    public void run(){
        service3.waitNotifyA();
    }
}

class Thread4 extends Thread {
    private Service3 service3;
    public Thread4(Service3 service3){
        super();
        this.service3=service3;
    }
    @Override
    public void run(){
        service3.notifyA();
    }
}

class Thread5 extends Thread {
    private Service3 service3;
    public Thread5(Service3 service3){
        super();
        this.service3=service3;
    }
    @Override
    public void run(){
        service3.waitNotifyB();
    }
}
class Thread6 extends Thread {
    private Service3 service3;
    public Thread6(Service3 service3){
        super();
        this.service3=service3;
    }
    @Override
    public void run(){
        service3.notifyB();
    }
}


