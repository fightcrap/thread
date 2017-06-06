package theSecondDay;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by zhengjiqi on 2017/6/6.
 */
public class VolutileSudy {
    /**
     * 学习volutile是为了解决一些平常需要用多线程技术解决，但是又会出现访问数据不统一的情况，举个例子，
     * 如果想要解决一个死循环的问题，那么只能利用多线程的技术
     */

    public static  void main(String[] args){
//       TestendlessLoop testendlessLoop=new TestendlessLoop();
//       testendlessLoop.method();
//       testendlessLoop.setTest();
       //学过编程的都会知道，这个循环是不会停止的，只有通过多线程的方法，就可以实现
//        TestendlessLoop testendlessLoop=new TestendlessLoop();
//        Thread a=new Thread(){
//            @Override
//            public void run(){
//                testendlessLoop.method();
//            }
//        };
//        Thread b=new Thread(){
//            @Override
//            public void run(){
//                testendlessLoop.setTest();
//            }
//        };
//        a.start();
//        try{
//            Thread.sleep(2000);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
//        b.start();
        //可以发现最后循环被停止了.但是当jvm被设定成-service时候就会出现另外一个问题，循环并没有被停止，原因在于修改的是公共堆栈，但是
        //-service模式，为了加快速度，线程访问的还是线程堆栈，也就是私有堆栈，导致两者不统一，所以循环并没有被结束，volutile就是为了这个而解决
        //的，volutile会强制获取公有堆栈的值，进行统一，这边就不展示了

        /**
         * 介绍下volutile的非原子性的问题，volutile虽然解决了线程的数据可见性问题，但是却有着原子性缺失的问题
         * 解决方法一般有两种:1。利用原子类来弥补这个问题，但是有时候原子类也是会有问题的，
         * 2。利用synchronized同步.
         */
//        TestVolutileQuestion testVolutileQuestion=new TestVolutileQuestion();
//        Thread[] a=new Thread[100];
//        for (int i=0;i<100;i++){
//            a[i]=new Thread(){
//                @Override
//                public void run(){
//                    testVolutileQuestion.method();
//                }
//            };
//        }
//        for (int j=0;j<100;j++){
//            a[j].start();
//        }
        //可以发现，最后的结果并不是10000,说明这个是有问题的，原因在于++操作过程中，会先进行read和load读取数，然后进行use和assign，这边就会出现
        // 因为原子性并没有保证，所以读取时候被修改了，但是数据没有被同步，在store和write的时候就会写入相同的值
        /**
         * 原子类解决问题
         */
//        TestAtomic testAtomic=new TestAtomic();
//        Thread[] a=new Thread[100];
//        for (int i=0;i<100;i++){
//            a[i]=new Thread(){
//                @Override
//                public void run(){
//                    testAtomic.method();
//                }
//            };
//        }
//        for (int j=0;j<100;j++){
//            a[j].start();
//        }
        //发现结果虽然是10000，结果是正确的，但是打印过程确实不对的，原因在于原子类能保证原子性，但是不能保证线程的安全性.
        //所以可以添加synchronized解决这个问题.
        TestAtomic testAtomic=new TestAtomic();
        Thread[] a=new Thread[100];
        for (int i=0;i<100;i++){
            a[i]=new Thread(){
                @Override
                public void run(){
                    testAtomic.method1();
                }
            };
        }
        for (int j=0;j<100;j++){
            a[j].start();
        }
    }
}

/**
 * 设定一个类，作为基础解决
 */
class TestendlessLoop{
    private boolean test=true;
    public void method(){
        int i=0;
        while (test){

            System.out.println(++i);
        }
        System.out.println("循环结束");
    }

    public void setTest(){
        test=false;
    }
}

/**
 * 编写一个类来验证volutile的原子性问题
 */
class TestVolutileQuestion{
    private long count=0;
    public void method(){
        for(int i=0;i<1000;i++){
            count++;
        }
        System.out.println(count);
    }
}

/**
 * 编写一个类来验证原子类能解决问题
 */
class TestAtomic{
    private AtomicLong atomicLong=new AtomicLong(0);
    public void method(){
        for(int i=0;i<1000;i++){
            atomicLong.incrementAndGet();
        }
        System.out.println(atomicLong.get());
    }

    synchronized public void method1(){
        for(int i=0;i<1000;i++){
            atomicLong.incrementAndGet();
        }
        System.out.println(atomicLong.get());
    }
}

