package theSecondDay;

import java.util.Date;

/**
 * Created by zhengjiqi on 2017/6/5.
 */
public class SynchronizedBlock {
    /**
     * 线程同步方法有时候会有很多多限制条件，比如，该方法需要用较长多时间来执行，但是需要保护数据的部分很少。所以这个时候就不适合用同步方法
     * 举个例子
     */

    public static void main(String[] args){
//        TestBadWayOfMethod testBadWayOfMethod=new TestBadWayOfMethod();
//        Thread thread=new Thread(){
//            @Override
//            public void run(){
//                testBadWayOfMethod.method1();
//            }
//        };
//        Thread thread1=new Thread(){
//            @Override
//            public void run(){
//                testBadWayOfMethod.method1();
//            }
//        };
//        thread.start();
//        thread1.start();
//    }
    //发现执行时间用了6秒，效率并没有提升，可以进行下面的修改
        TestBadWayOfMethod1 testBadWayOfMethod=new TestBadWayOfMethod1();
        Thread thread=new Thread(){
            @Override
            public void run(){
                testBadWayOfMethod.method1("ssss");
            }
        };
        Thread thread1=new Thread(){
            @Override
            public void run(){
                testBadWayOfMethod.method1("ggg");
            }
        };
        thread.start();
        thread1.start();
        //发现时间变成了3秒，效率增加，但是要注意线程安全的问题，没有被synchronized修饰的代码，将会被异步执行，所以容易出现问题，把需要同步的
        //的内容加入到同步代码块中，

        /**
         * 同步代码块synchronized（this）锁定的对象其实和同步方法是一样，锁定的都是当前的对象，让我们来验证一下
         */



    }
}

/**
 * 创建一个类来验证同步方法的局限性
 */
class TestBadWayOfMethod{
    synchronized public void method1(){
        System.out.println("进入线程:"+new Date().getTime());
        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("结束线程"+new Date().getTime());

    }
}

/**
 * 创建一个类来验证同步方法局限性的解决
 */
class TestBadWayOfMethod1{
    private String a="a";
    public void method1(String f){
        System.out.println("进入线程:"+new Date().getTime());
        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        synchronized(this){
            a=f;
            System.out.println("结束线程" + a);

        }

    }
}

/**
 *创建一个
 */