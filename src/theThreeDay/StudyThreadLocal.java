package theThreeDay;

/**
 * Created by zhengjiqi on 2017/6/12.
 */
public class StudyThreadLocal {
    /**
     * 学习到现在，大部分内容我们都已经了解了，但是发现一个问题，我要怎么把一个特定的属性绑定在一个线程上面呢，
     * 就好像session一样，绑定在一个会话线程中呢，现在就让我们来了解一下线程变量ThreadLocal这个类
     */
    public static void main(String[] args){
        /**
         * 线程变量往往是用作一个静态方法在一个特定的类中，这样可以方便每个线程去获取，而不是实例出不同的实例。
         */
        Thread thread=new Thread(){
            @Override
            public void run(){
                try {
                    TempThreadLocal.threadLocal.set("线程一");
                    System.out.println("变量修改");
                    Thread.sleep(5000);
                    System.out.println(Thread.currentThread().getName()+TempThreadLocal.threadLocal.get());
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };

        Thread thread1=new Thread(){
            @Override
            public void run(){
                try {
                    TempThreadLocal.threadLocal.set("线程二");
                    System.out.println("变量修改");
                    Thread.sleep(5000);
                    System.out.println(Thread.currentThread().getName()+TempThreadLocal.threadLocal.get());
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        thread1.start();
        //运行结果发现，线程二并没有修改线程一中的值，两个线程锁拥有的值是各自独立的，相当于他们自己的标示一样，除非本身修改，不然不会被别的线程所影响
        Thread thread3=new Thread(){
            @Override
            public void run(){
                try {

                    System.out.println("变量修改");
                    Thread.sleep(5000);
                    System.out.println(Thread.currentThread().getName()+TempThreadLocal.threadLocal.get());
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        thread3.start();
        //执行第三个线程的时候发现，默认的线程变量是为空的，那么，能不能修改这个默认的值呢？答案当然是肯定的
        NewThreadLocal newThreadLocal=new NewThreadLocal();
        Thread thread4=new Thread(){
            @Override
            public void run(){
               System.out.println(newThreadLocal.get());
            }
        };
        thread4.start();
        //结果可以发现，输出的结果不是空，而是我们自定义的字符串
    }
}

/**
 * 定义一个线程变量的类
 */
class TempThreadLocal{
    public static ThreadLocal threadLocal=new ThreadLocal();
    public Object get(){
        return threadLocal.get();
    }

    public void set(Object object){
        threadLocal.set(object);
    }
}

/**
 * 创建一个线程变量，使得初始化不会空
 */
class NewThreadLocal extends ThreadLocal{
    @Override
    protected Object initialValue() {
        return "已经不为空了";
    }
}