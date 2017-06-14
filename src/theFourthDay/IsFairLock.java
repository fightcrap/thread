package theFourthDay;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhengjiqi on 2017/6/14.
 */
public class IsFairLock {
    /**
     * 在初始化锁的时候，可以传入一个boolean值，如果值为false则为不公平锁，两者的差距在于，公平锁会按照原来加锁的顺序
     * 继续执行，不公平锁则不会
     */
    public static void main(String[] args){
//     ServiceTemp serviceTemp=new ServiceTemp(true);
//     for(int i=0;i<10;i++){
//         ThreadTemp threadTemp=new ThreadTemp(serviceTemp);
//         threadTemp.start();
//     }
     //运行过程中发现，顺序是按照加锁的顺序执行的   获取和释放顺序不变

        ServiceTemp serviceTemp=new ServiceTemp(false);
        for(int i=0;i<10;i++){
            ThreadTemp threadTemp=new ThreadTemp(serviceTemp);
            threadTemp.start();
        }
//运行过程中发现，顺序是按照加锁的顺序执行的   获取和释放顺序是不同的  这就是公平锁和不公平锁的区别

    }
}
class ServiceTemp{
    private Lock lock;
    public ServiceTemp(boolean t){
        super();
        lock=new ReentrantLock(t);
    }
    public void method(){
        lock.lock();
        System.out.println(Thread.currentThread().getName()+"释放锁");
        lock.unlock();
    }
}
class ThreadTemp extends Thread{
    private ServiceTemp serviceTemp;
    public ThreadTemp(ServiceTemp serviceTemp){
        this.serviceTemp=serviceTemp;
    }
    @Override
    public void run(){
        try{
            System.out.println(Thread.currentThread().getName()+"获得来锁");
            Thread.sleep(0);
            serviceTemp.method();
        }catch (InterruptedException e){
            e.printStackTrace();
        }


    }
}
