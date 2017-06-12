package theThreeDay;

/**
 * Created by zhengjiqi on 2017/6/12.
 */
public class StudyInheritableThreadLocal {
    /**
     *  在学习了线程变量后，发现线程只能是绑定单独的一个值，那为要获得父线程的值，怎么办呢？
     */
    public static void main(String[] args){
           InheritableThreadLocalTest.inheritableThreadLocal.set("main线程");
           ThreadTest1 threadTest1=new ThreadTest1();
           ThreadTest2 threadTest2=new ThreadTest2();
           threadTest1.start();
           threadTest2.start();
           //实验结果可以发现，get的值一直是父线程的set值，说明InheritableThreadLocal可以获取父线程的内容，可以进行父子关系的线程进行通信
    }
}

class InheritableThreadLocalTest{
    public static InheritableThreadLocal inheritableThreadLocal=new InheritableThreadLocal();
}
class ThreadTest1 extends Thread{

    @Override
    public void run(){
        try {
            synchronized ("") {
                System.out.println(InheritableThreadLocalTest.inheritableThreadLocal.get());
                Thread.sleep(1000);
                InheritableThreadLocalTest.inheritableThreadLocal.set("第一个线程");
                Thread thread=new Thread(){
                    @Override
                    public void run(){
                        System.out.println(InheritableThreadLocalTest.inheritableThreadLocal.get());
                    }
                };
                thread.start();
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }
}

class ThreadTest2 extends Thread{

    @Override
    public void run(){
        try {
            synchronized ("") {
                System.out.println(InheritableThreadLocalTest.inheritableThreadLocal.get());
                Thread.sleep(1000);
                InheritableThreadLocalTest.inheritableThreadLocal.set("第二个线程");
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }
}