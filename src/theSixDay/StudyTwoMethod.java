package theSixDay;

/**
 * Created by zhengjiqi on 2017/6/21.
 */
public class StudyTwoMethod {
    /**
     * 在我们学习单例模式的时候，我们都是在单线程情况下编程，所以不会出现问题，在学过多线程后，应该都能感觉到单例模式实例在多
     * 线程中可能会出现很多问题，但在这之前，我们先了解两种加载模式：
     * 1.立即加载／饿汉模式
     * 2.延迟加载／懒汉模式
     */

    public static void main(String[] args){
        /**
         * 什么是立即加载／饿汉模式呢，最明显但一点，就是在调用该类方法前，就已经被实例好了，最常见但方式就是声明时候用new已经初始化了
         */
//        System.out.println(LoadRightNow.get());
//        //在调用get方法前，就已经实例好了，这个就叫做立即加载
//
//
//        System.out.println(LoadDelay.get());
//        //这个就是懒加载，需要在执行方法但时候，进行初始化。


        /**
         * 立即加载在多线程中一般不会有问题，因为已经被初始化了。但是懒加载就不一样了。
         * 下面转换到多线程多环境
         */
         ThreadFirst threadFirst=new ThreadFirst();
         ThreadFirst threadFirst1=new ThreadFirst();
         ThreadFirst threadFirst2=new ThreadFirst();
         threadFirst.start();
         threadFirst1.start();
         threadFirst2.start();
         //运行结果发现出现了3个不同多hash值，说明被实例了3次，这个和单例模式不符合，所以在多线程情况下是出现了问题




    }
}

//立即加载情况
class LoadRightNow{
    private static Integer integer=new Integer(1);
    private  LoadRightNow(){

    }
    public void set(Integer integer){
        this.integer=integer;
    }
    public static Integer get(){
        return integer;
    }
}

//延迟加载
class LoadDelay{
    private static Integer integer;
    private LoadDelay(){

    }

    public static Integer get(){
        if(integer==null){
            integer=new Integer(1);
        }
        return integer;
    }
}

//多线程环境，使用延迟加载
class ThreadLoadDelay{
    private static Object object;
    private ThreadLoadDelay(){

    }

    public static  long get(){
        if (object==null){
            return (new Object()).hashCode();
        }else return object.hashCode();
    }
}

class ThreadFirst extends Thread{
    @Override
    public void run(){
        System.out.println(ThreadLoadDelay.get());
    }
}