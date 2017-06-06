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

    public static void main(String[] args) {
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
//        TestBadWayOfMethod1 testBadWayOfMethod = new TestBadWayOfMethod1();
//        Thread thread = new Thread() {
//            @Override
//            public void run() {
//                testBadWayOfMethod.method1("ssss");
//            }
//        };
//        Thread thread1 = new Thread() {
//            @Override
//            public void run() {
//                testBadWayOfMethod.method1("ggg");
//            }
//        };
//        thread.start();
//        thread1.start();
        //发现时间变成了3秒，效率增加，但是要注意线程安全的问题，没有被synchronized修饰的代码，将会被异步执行，所以容易出现问题，把需要同步的
        //的内容加入到同步代码块中，

        /**
         * 同步代码块synchronized（this）锁定的对象其实和同步方法是一样，锁定的都是当前的对象，让我们来验证一下
         */
//        TestMethod testMethod=new TestMethod();
//        Thread a=new Thread(){
//            @Override
//            public void run(){
//                testMethod.setA();
//            }
//        };
//        Thread b=new Thread(){
//            @Override
//            public void run(){
//                testMethod.changeA();
//            }
//        };
//        a.start();
//        b.start();
        //运行过程可以发现，运行时间是6秒，说明两者不是异步，而是同步，所以两者锁定的对象是同一个

        /**
         * synchronized同步代码块中的（）是可以填入对象的，那么是不是能够动态的选定呢？答案是可以的，但是有限制条件的，需要在执行前变更
         * 不然是不起作用的
         */

        TestDynamic testDynamic = new TestDynamic();
        Thread a = new Thread() {
            @Override
            public void run() {
                testDynamic.printOut();
            }
        };
        Thread b = new Thread() {
            @Override
            public void run() {
                testDynamic.printOut1();
            }
        };
        Thread c = new Thread() {
            @Override
            public void run() {
                try {
                    testDynamic.printOut2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        try {
            a.start();
            c.start();

            Thread.sleep(50);

            //b.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //a和c一起会发现,就算对象被改变，但是启动时，锁的对象锁一样的，所以并没有被释放开，是同步执行，而a和b则不同，是异步，因为在没有锁之前，
        //对象已经被更改


    }
}

/**
 * 创建一个类来验证同步方法的局限性
 */
class TestBadWayOfMethod {
    synchronized public void method1() {
        System.out.println("进入线程:" + new Date().getTime());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束线程" + new Date().getTime());

    }
}

/**
 * 创建一个类来验证同步方法局限性的解决
 */
class TestBadWayOfMethod1 {
    private String a = "a";

    public void method1(String f) {
        System.out.println("进入线程:" + new Date().getTime());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this) {
            a = f;
            System.out.println("结束线程" + a);

        }

    }
}

/**
 * 创建一个类来验证synchronized方法和synchronized（this）是一样的情况
 */

class TestMethod {
    private String a = "a";

    synchronized public void setA() {
        a = "f";
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a);

    }

    public void changeA() {
        synchronized (this) {
            a = "g";
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(a);
        }
    }

}

/**
 * 创建一个类来验证动态锁定代码块的实例
 */

class TestDynamic {
    private String id = "123";

    public void printOut() {
        synchronized (id) {

            System.out.println("第一次进入");
            id = "456";
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void printOut1() {
        synchronized (id) {
            System.out.println("第二次进入");
        }
    }

    public void printOut2() throws InterruptedException {
        synchronized (id) {
            System.out.println("第三次进入");
            Thread.sleep(1000);
        }
    }
}