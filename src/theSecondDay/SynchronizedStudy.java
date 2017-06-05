package theSecondDay;

/**
 * Created by zhengjiqi on 2017/6/1.
 */
public class SynchronizedStudy {
    /**
     * 在线程中，线程的安全问题是非常重要的，如何写出一个安全的多线程java是很必要的，虽然之前我们了解了synchronized的作用，但
     * 不是很熟悉，这次就让我们来深入了解一下
     */
    private int test = 0;

    public void sum(int a) throws Exception {
        int f = a;
        System.out.println("test:" + f);
        Thread.sleep(3000);
        System.out.println("over:" + f);
    }


    synchronized public void delete(int a) throws Exception {
        test = a;
        System.out.println("test:" + Thread.currentThread().getName() + test);
        Thread.sleep(2000);
        System.out.println("over:" + Thread.currentThread().getName() + test);
    }


    public static void main(String[] args) {
        /**
         * 方法变量是不会出现非线程安全的，这个是最安全的情况
         */
//        SynchronizedStudy synchronizedStudy=new SynchronizedStudy();
//        SynchronizedStudyFirst synchronizedStudyFirst=new SynchronizedStudyFirst(synchronizedStudy);
//        synchronizedStudyFirst.start();
//        SynchronizedStudySecond synchronizedStudySecond=new SynchronizedStudySecond(synchronizedStudy);
//        synchronizedStudySecond.start();

        /**
         * 非线程安全的情况，在上一节已经讲过来，如果是实例变量，那么多线程进行访问的时候，就会出现非线程安全的情况，可以用synchronized进行解决
         * 运用同步，线程的执行就成队列的形式，必须等待前一个执行完才可以继续执行。
         */

        /**
         * 多个对象多个锁情况,当创建多个实例时，用不同的线程去访问它的同步方法会发生什么呢？
         */
//        SynchronizedStudy synchronizedStudy = new SynchronizedStudy();
//        SynchronizedStudy synchronizedStudy1= new SynchronizedStudy();
//        SynchronizedStudyFour synchronizedStudyFour= new SynchronizedStudyFour(synchronizedStudy);
//        synchronizedStudyFour.start();
//        SynchronizedStudyThree synchronizedStudyThree = new SynchronizedStudyThree(synchronizedStudy1);
//        synchronizedStudyThree.start();
        //执行结果并没有因为第一个同步资源而等待，出现了异步的执行的情况，两个实例相当于两个不相干的事物，所以同步的限制并没有作用到。

        /**
         * synchronized方法是为了避免脏读的情况而使用的，但是是不是使用了就不会出现脏读的情况呢，答案当然是否定的
         */

//        TestBadRead testBadRead = new TestBadRead();
//        Thread a = new Thread() {
//            @Override
//            public void run() {
//                testBadRead.setValue("lalala", "sssss");
//            }
//        };
//        Thread b = new Thread() {
//            @Override
//            public void run() {
//                System.out.println(testBadRead.getValue());
//            }
//        };
//        a.start();
//        b.start();
        //原因在于，虽然synchronized锁定了setValue方法，但是getValue并没有同步，线程可以通过异步的方式进行访问，所以出现了脏读的情况
        //只要把getValue也用synchronized修饰就可以了。

        /**
         * 锁的重入，在有一个synchronized修饰的方法可以调用另外一个被synchronized修饰的方法，而不会出现死锁的情况，这样就叫锁的重入
         */

        TestSynchronized testSynchronized=new TestSynchronized();
        Thread thread=new Thread(){
            @Override
            public void run(){
                testSynchronized.method1();
            }
        };
        thread.start();


    }
}

/**
 * 创建两个线程来验证方法变量是不会出现线程安全的
 */
class SynchronizedStudyFirst extends Thread {
    private SynchronizedStudy synchronizedStudy;

    public SynchronizedStudyFirst(SynchronizedStudy synchronizedStudy) {
        super();
        this.synchronizedStudy = synchronizedStudy;
    }

    @Override
    public void run() {
        try {
            synchronizedStudy.sum(20);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class SynchronizedStudySecond extends Thread {
    private SynchronizedStudy synchronizedStudy;

    public SynchronizedStudySecond(SynchronizedStudy synchronizedStudy) {
        super();
        this.synchronizedStudy = synchronizedStudy;
    }

    @Override
    public void run() {
        try {
            synchronizedStudy.sum(50);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


/**
 * 创建两个线程来验证多变量多个锁的情况
 */
class SynchronizedStudyFour extends Thread {
    private SynchronizedStudy synchronizedStudy;

    public SynchronizedStudyFour(SynchronizedStudy synchronizedStudy) {
        super();
        this.synchronizedStudy = synchronizedStudy;
    }

    @Override
    public void run() {
        try {
            synchronizedStudy.delete(20);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class SynchronizedStudyThree extends Thread {
    private SynchronizedStudy synchronizedStudy;

    public SynchronizedStudyThree(SynchronizedStudy synchronizedStudy) {
        super();
        this.synchronizedStudy = synchronizedStudy;
    }

    @Override
    public void run() {
        try {
            synchronizedStudy.delete(50);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

/**
 * 创建一个类来验证同步模块还是会出现脏读的情况的
 */
class TestBadRead {
    private String a = "a";
    private String b = "b";

    synchronized public void setValue(String a, String b) {
        this.a = a;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.b = b;
    }

    public String getValue() {
        return this.a + "      " + this.b;
    }
}

/**
 * 创建一个类来验证锁的重入情况
 */
class TestSynchronized {
    private String a="a";
    synchronized public void method1(){
        System.out.println(a);
        method2();
        System.out.println(a);

    }
    synchronized public void method2(){
        a="b";
    }

}