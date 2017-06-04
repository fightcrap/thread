package theFirstDay;

/**
 * Created by zhengjiqi on 2017/5/29.
 */
public class StopThread {
    /**
     * 停止线程是在编写多线程所要掌握的基本技能之一。
     * 常用的停止线程的方法有三种：
     * 1。使用停止标记，也就是等待它自动运行结束
     * 2。使用stop函数，但是不推荐使用这个方法，使用stop杀死线程会导致锁被释放，可能会出现资源操作出现错误的情况，而且这个函数已经过时了。
     * 3。使用interrupt函数进行停止，但是这个函数并不是真正的停止线程，而是给线程一个标记，然后通过特定的写法来进行实现真正的停止功能。
     * 主要推荐第三种方式
     */
    public static void main(String[] args) {
    //先让我们来验证interrupt函数是不是真正的停止线程
//        FirstThreadTest firstThreadTest=new FirstThreadTest();
//        firstThreadTest.start();
//        try{
//            Thread.sleep(1000);
//            firstThreadTest.interrupt();
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//        }
        //测试结果发现循环一直没有停止，也就是线程是一直在运行的，并没有被停止,所以我们要借用一定的方式，
        //首先介绍两个api,isInterrupted()以及interrupted()
        /**
         * isInterrupted判断的是线程对象是否被停止，interrupted()该函数是判断执行该语句的线程有没有被停止，所以这是一个坑，如果在main
         * 函数中执行这个函数，则判断的是main线程是否被暂停，而且执行interrupted（）函数后会清除线程的
         */
//         StopThreadTwo stopThreadTwo=new StopThreadTwo();
//         stopThreadTwo.start();
//         try {
//             stopThreadTwo.interrupt();
//         }catch (Exception e)
//         {
//             e.printStackTrace();
//         }



        /**
         * 了解到了线程到暂停情况，但是实际上还是没有中断线程，所以需要用特定到手法来实现，常用的有异常的形式，或者直接return。
         */
        StopThreadThree stopThreadTwo=new StopThreadThree();
        stopThreadTwo.start();
        try {
            stopThreadTwo.interrupt();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        /*
        在其他对一些状态中暂停线程是会报错对，比如沉睡状态使用interrupe（）会发生异常。
         */

    }
}
/**
 * 创建一个线程来验证interrupt的特性
 */
class FirstThreadTest extends Thread{
    @Override
    public void run(){
        for(int i=0;i<50000;i++){
            System.out.println(i);
        }
    }
}

/**
 * 创建一个线程来测试isInterrupted（）和interrupted（）
 */
class StopThreadTwo extends Thread{
    @Override
    public void run(){
        for(int i=0;i<50000;i++)
        {
            if(this.isInterrupted())
        {
            System.out.println("线程已经被停止了");
        }
            if(this.interrupted())
            {
                System.out.println("线程处于中断状态");
            }

            if(!this.isInterrupted())
            {
                System.out.println("中断情况已经被消除");
            }
            System.out.println(i);
        }
    }
}

/**
 * 创建一个线程来测试中断的方式
 */
class StopThreadThree  extends Thread  {
    @Override
    public void run() {
        try {
        for(int i=0;i<50000;i++)
        {

                if(this.isInterrupted())
                {
                    throw  new Exception() ;
                    //return
                }

                System.out.println(i);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}