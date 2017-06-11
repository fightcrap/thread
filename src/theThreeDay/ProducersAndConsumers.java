package theThreeDay;


import java.beans.Customizer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengjiqi on 2017/6/10.
 */
public class ProducersAndConsumers {
    /**
     * 生产者和消费者模式是等待／通知机制中最常见，也是最基本的情况，所以我们有必要来详细的学习一下
     */
    public static  void main(String[] args){
        /**
         * 我们将从消费者和生产者一对一，一对多，多对一，多对多四个情况来学习
         */


        /**
         *一对一情况模拟
         */
//        List list=new ArrayList();
//        Object object=new Object();
//        Produces produces=new Produces(list,object);
//        Consumers consumers=new Consumers(list,object);
//        Thread thread1=new Thread(new Thread1(produces));
//        Thread thread2=new Thread(new Thread2(consumers));
//        thread1.start();
//        thread2.start();
        //运行结果发现，生产和消费是交互出现，而且必然是先生产后消费的情况.这就是简单的消费者和生产者的情况

        /**
         *一对多的情况模拟
         */
        List list=new ArrayList();
        Object object=new Object();
        Produces produces=new Produces(list,object);
        Consumers consumers=new Consumers(list,object);
        Thread thread1=new Thread(new Thread1(produces));
        Thread thread7=new Thread(new Thread2(consumers));
        Thread thread3=new Thread(new Thread2(consumers));
        Thread thread4=new Thread(new Thread2(consumers));
        Thread thread5=new Thread(new Thread2(consumers));
        Thread thread2=new Thread(new Thread2(consumers));
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread7.start();

        //在实验过程中，我们发现最后结果会被阻塞，程序没有继续运行下去，出现了很有名的错误，那就是："假死"，因为在程序运行
        //的过程中，调用生产者后，又调用生产者，然后调用了两次消费者，就无法解开生产者的等待了，解决方法就是使用notifyAll（）代替notify（）

    }
}

/**
 * 创建生产者和消费者
 */

class Produces{
    private List list;
    private Object object;
    public Produces(List list,Object object){
        this.list=list;
        this.object=object;
    }
    public void add(){
        synchronized (object){
            try {
                    while (list.size() != 0) {
                        object.wait();
                    }
                    list.add("1");
                    System.out.println("生产");
                    Thread.sleep(1000);
                    object.notify();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

class Consumers{
    private List list;
    private Object object;
    public Consumers(List list,Object object){
        this.list=list;
        this.object=object;
    }
    public void remove(){
        synchronized (object){
            try{
                    while(list.size()==0){
                        object.wait();
                    }
                    list.remove(0);
                    System.out.println("消费");
                    Thread.sleep(1000);
                    object.notify();
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }
}

/**
 * 构建两个线程
 */
class Thread1 implements Runnable{
    private Produces produces;
    public Thread1(Produces produces){
        this.produces=produces;
    }

    @Override
    public void run() {
        while(true){
            produces.add();
        }

    }
}

class Thread2 implements Runnable{
    private Consumers consumers;
    public Thread2(Consumers consumers){
        this.consumers=consumers;
    }
    @Override
    public void run() {
        while(true){
            consumers.remove();
        }
    }
}
