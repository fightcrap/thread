package theFirstDay;

/**
 * Created by zhengjiqi on 2017/5/29.
 */
public class TheApiOfThread {
    /**
     * 线程常用的api介绍
     */
    public static void main(String[] args) {
        //第一个currentThread方法,该方法是返回一个当前运行代码段的线程
        System.out.println(Thread.currentThread().getName());
        FirstThread firstThead = new FirstThread();


        /*
        验证run和start的区别
         */
//        firstThead.run();
//        firstThead.start();
        /*
        结果
        main
        main
        Thread-0
         */

        //第二个api是isAlive,该方法是判断是不是处于存活状态,线程处于运行或者准备开始运行是就是存活状态

//        System.out.println(firstThead.isAlive());
//        firstThead.start();
//        System.out.println(firstThead.isAlive());

        /*
        结果
        main
        false
        true
        Thread-0
        由于线程的不稳定性，可能有时候会先执行完线程内容，导致后一语句运行时线程结束变成false
         */

        /*
        第三个api是sleep，这个函数经常用于让线程暂停执行,以毫秒为单位
         */
//        try {
//            System.out.println("测试");
//            Thread.sleep(5000);
//            System.out.println("休眠后，执行");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        /*
        第四个api是getId，这个函数是用来获取线程的唯一标示
         */

        System.out.println(Thread.currentThread().getId());


    }
}

/**
 * 创建一个自定义线程
 */
class FirstThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
