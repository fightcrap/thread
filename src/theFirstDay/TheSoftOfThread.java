package theFirstDay;

import javax.swing.plaf.PanelUI;

/**
 * Created by zhengjiqi on 2017/5/28.
 */
public class TheSoftOfThread {
    /**
     * 在运用多线程中，大部分程序员都会关注线程安全，可以说线程安全说是多线程中最容易出现问题的地方
     *
     * 线程数据有共享和非共享两种情况，一般是针对类中的变量，局部变量在线程中一般是不会共享的。
     */
    public static void main(String[] args){
        /*
        数据共享的线程类演示
         */
//
//        FirstThead firstThead1=new FirstThead("线程一");
//        FirstThead firstThead2=new FirstThead("线程二");
//        FirstThead firstThead3=new FirstThead("线程三");
//        firstThead1.start();
//        firstThead2.start();
//        firstThead3.start();
        /*
        通过运行的结果可以发现，每一个线程的count都各自所用，并没用共享，数据并没有在线程之间进行交互，
        简单的说，相当于每次都创建类一个类，只是类的操作互不干涉，所以里面的数据也没有被更改，所以说不共享的情况
         */


//        SecontThead secontThead=new SecontThead();
//        Thread thread1 = new Thread(secontThead,"线程一");
//        Thread thread2= new Thread(secontThead,"线程二");
//        Thread thread3 = new Thread(secontThead,"线程三");
//        Thread thread4= new Thread(secontThead,"线程四");
//        Thread thread5 = new Thread(secontThead,"线程五");
//        thread1.start();
//        thread2.start();
//        thread3.start();
//        thread4.start();
//        thread5.start();
        /*
        通过运行可以发现，数据被共享，但是,有时候会出现数据出错的情况
        线程二3
        线程三2
        线程一3
        线程四1
        线程五0
        这个就是在线程中出现的问题 非线程安全问题
         */



        /*
        那么如何解决这个问题呢，我们可以用关键字synchronized来进行对代码对同步，
         */
//        ThreeThread threeThread=new ThreeThread();
//        Thread thread6 = new Thread(threeThread,"线程一");
//        Thread thread7= new Thread(threeThread,"线程二");
//        Thread thread8 = new Thread(threeThread,"线程三");
//        Thread thread9= new Thread(threeThread,"线程四");
//        Thread thread10 = new Thread(threeThread,"线程五");
//        thread6.start();
//        thread7.start();
//        thread8.start();
//        thread9.start();
//        thread10.start();
        /*
        多次执行后，没有发现出现非线程安全的情况来，synchronized关键字可以修饰任意对象，表示那一段代码段是被同步的，也可以修饰方法等，
        使用synchronized关键字就会使得多线程程序出现排队的形式，也就是前一个没有执行完就不会继续执行下去，从而达到线程安全的情况。
        被加锁的代码被称为"互斥区"或者是"临界区"
         */


        /*
        现在来展示一个很神奇的坑,system.out.println（）和i--的异常情况
         */
        FourThread fourThread=new FourThread();
        Thread thread6 = new Thread(fourThread,"线程一");
        Thread thread7= new Thread(fourThread,"线程二");
        Thread thread8 = new Thread(fourThread,"线程三");
        Thread thread9= new Thread(fourThread,"线程四");
        Thread thread10 = new Thread(fourThread,"线程五");
        thread6.start();
        thread7.start();
        thread8.start();
        thread9.start();
        thread10.start();
        /*
        运行结果
        线程一5
        线程三4
        线程二5
        线程四3
        线程五2
        在查阅相应的api中，println的方法是同步的，但是却也出现来非线程安全的问题，为什么呢，原来是因为i--这操作在
        println前发生的，所以还是有一定概率发生非线程安全的问题
         */

    }

}

/**
 * 创建一个自定义线程类
 */
class FirstThead extends Thread{
    private int count=5;

    public FirstThead(String name){
        super();
        this.setName(name);
    }
    @Override
    public void run(){
        for(int i=0;i<5;i++){
            System.out.println(this.getName()+count--);
        }
    }

}
/**
 * 创建一个不是用循环的线程类
 */
class SecontThead extends Thread{
    private int count=5;
    public SecontThead(){

    }
    public SecontThead(String name){
        super();
        this.setName(name);
    }
    @Override
    public void run(){
        super.run();
        count--;
        System.out.println(this.currentThread().getName()+count);

    }
}

/**
 * 创建一个用synchronized关键字进行绑定的自定义线程
 */
class ThreeThread extends Thread{
    private int count=5;

    @Override
    public synchronized void run(){
        super.run();
        count--;
        System.out.println(this.currentThread().getName()+count);
    }
}

/**
 * 创建一个验证System.out.println（）和i--的异常的线程
 */

class FourThread extends Thread{
    private int count=5;
    @Override
    public void run(){
        super.run();

        System.out.println(this.currentThread().getName()+count--);
    }
}