package theFourthDay;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhengjiqi on 2017/6/14.
 */
public class ProducersAndConsumers {
    /**
     * 生产者消费者模式是学习多线程最常见的模式，既然学了新的加锁方式，让我们来模拟生产者和消费者的问题
     */
    public static void main(String[] args){
        Producers producers=new Producers();
        ThreadTest threadTest=new ThreadTest(producers);
        ThreadTest1 threadTest1=new ThreadTest1(producers);
        threadTest.start();
        threadTest1.start();
    }

}
class Producers{
     private boolean isTrue=false;
     private Lock lock=new ReentrantLock();
     private Condition condition=lock.newCondition();
     public void set(){
         lock.lock();
         try{
             while(isTrue){
                 condition.await();
             }
             System.out.println("生产");
             //避免出现假死情况
             Thread.sleep(1000);
             isTrue=true;
             condition.signalAll();
         }catch (InterruptedException e){
             e.printStackTrace();
         }finally {
             lock.unlock();
         }

     }
     public void get(){
         lock.lock();
         try{
             while(!isTrue){
                 condition.await();
             }
             System.out.println("消费");
             //避免出现假死情况
             Thread.sleep(1000);
             isTrue=false;
             condition.signalAll();
         }catch (InterruptedException e){
             e.printStackTrace();
         }finally {
             lock.unlock();
         }
     }
}
class ThreadTest extends Thread{
    private Producers producers;
    public ThreadTest(Producers producers){
        super();
        this.producers=producers;
    }
    @Override
    public void run(){
        while(true){
            producers.get();
        }
    }
}
class ThreadTest1 extends Thread{
    private Producers producers;
    public ThreadTest1(Producers producers){
        super();
        this.producers=producers;
    }
    @Override
    public void run(){
        while(true){
            producers.set();
        }
    }
}