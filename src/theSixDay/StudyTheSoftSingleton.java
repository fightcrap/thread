package theSixDay;

import java.io.*;

/**
 * Created by zhengjiqi on 2017/6/21.
 */
public class StudyTheSoftSingleton {

    /**
     * 解决多线程问题，我们第一个想到的是就是我们最熟悉的synchronized关键字，同步的方式，还有lock锁等等，这些都是我们
     * 最熟悉的，但是相对于效率而言，如果只是套用简单的同步形式，那么效率将会不高。现在介绍一种Dcl双检测锁机制，虽然这个
     * 用的是synchronized关键字，但是效率会提高
     */

    public static void main(String[] args) {

//        ThreadFour threadFour=new ThreadFour();
//        ThreadFour threadFour1=new ThreadFour();
//        ThreadFour threadFour2=new ThreadFour();
//        threadFour.start();
//        threadFour1.start();
//        threadFour2.start();
        //单例模式的效果出现了，相对于以前的方法更加好一点


        /**
         * 除了这种方法，还有一种方法，那就是利用立即加载的形式。在调用方法前就已经实例了
         * 方法有：
         * 1.使用静态内部类
         * 2.使用静态代码块
         * 3.序列化和反序列化的问题
         */


        //使用静态内部类
//        ThreadFive threadFive=new ThreadFive();
//        ThreadFive threadFive1=new ThreadFive();
//        ThreadFive threadFive2=new ThreadFive();
//        threadFive.start();
//        threadFive1.start();
//        threadFive2.start();

        //运行结果发现，符合单例模式的规则，不会出现线程问题的原因是在于运用了饿汉模式，在调用方法前，已经初始化了，所以不会出现线程安全


        //使用静态代码块
//        ThreadSix threadSix=new ThreadSix();
//        ThreadSix threadSix1=new ThreadSix();
//        ThreadSix threadSix2=new ThreadSix();
//        threadSix.start();
//        threadSix1.start();
//        threadSix2.start();


        //序列化和反序列化的问题，序列化和反序列化不能保存静态域，所以在使用上述情况，会出现问题，那么怎么解决呢
        try{
            ThreadDCLStudy object=ThreadDCLStudy.getString();
            FileOutputStream fileOutputStream=new FileOutputStream(new File("mytest.txt"));
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println(object.hashCode());
        }catch (IOException e){
            e.printStackTrace();
        }
        try{
            FileInputStream fileInputStream=new FileInputStream(new File("mytest.txt"));
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
            ThreadDCLStudy object1= (ThreadDCLStudy)objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            System.out.println(object1.hashCode());
        }catch (Exception e){
            e.printStackTrace();
        }



    }





}

class ThreadDCLStudy  implements Serializable{
    private static final long serialVersionUID=888l;
    private static volatile Object object; //运用volatile关键字来保证线程可见性

    private static  Object testStatic;


    private ThreadDCLStudy() {

    }



    public static Object get() {
        //通过延迟来模拟长时间的运行，这样使用synchronized方法的效率不高
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (object == null) {
            synchronized (ThreadDCLStudy.class) {
                //直接使用synchronized代码块，起到作用是一样的，

                //所以需要DCL双锁机制,为什么叫双锁，原因在于多一层判断
                if (object == null) {
                    object = new Object();
                }
            }

        }
        return object;
    }


    //构建内部类
    static class Singleton {
        private static String object = new String("");
    }

    public static Object getObject() {
        return Singleton.object;
    }


    //使用静态代码块
    static {
        testStatic=new Object();
    }

    public static Object getTestStaticObject(){
        return testStatic;
    }


    static class Test{
        private static ThreadDCLStudy threadDCLStudy=new ThreadDCLStudy();
    }
    //序列化问题,使用序列化的时候，静态域不能被序列化，所以会出现两个不同对象。但是要满足单例模式
    //我们需要实现readResolve函数，来保证是单独的一个例子
    public static ThreadDCLStudy  getString(){

        return Test.threadDCLStudy;
    }

    protected Object readResolve()throws ObjectStreamException{
        System.out.println("调用了readResolve函数");
        return Test.threadDCLStudy;
    }

}


class ThreadFour extends Thread {
    @Override
    public void run() {
        System.out.println(ThreadDCLStudy.get().hashCode());
    }
}


class ThreadFive extends Thread {
    @Override
    public void run() {
        System.out.println(ThreadDCLStudy.getObject().hashCode());
    }
}

class ThreadSix extends Thread{
    @Override
    public void run(){
        System.out.println(ThreadDCLStudy.getTestStaticObject().hashCode());
    }
}

