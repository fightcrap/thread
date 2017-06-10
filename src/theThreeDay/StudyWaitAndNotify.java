package theThreeDay;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengjiqi on 2017/6/10.
 */
public class StudyWaitAndNotify {
    /**
     * 等待和通知是线程通信最常用的一种形式，而线程通信又是对多线程技术运用不可缺少的技能，所以掌握这个技术非常重要
     */
    public static void main(String[] args) {
        /**
         * 先来模拟一个不要使用等待／通知技术来实现通知的作用，我们运用循环来确保实现监听的作用
         */
//        List list=new ArrayList();
//        StudyWaitAndNotifyFirst studyWaitAndNotifyFirst=new StudyWaitAndNotifyFirst(list);
//        studyWaitAndNotifyFirst.setName("a");
//        studyWaitAndNotifyFirst.start();
//        StudyWaitAndNotifySecond studyWaitAndNotifySecond=new StudyWaitAndNotifySecond(list);
//        studyWaitAndNotifySecond.setName("b");
//        studyWaitAndNotifySecond.start();
        /*
        执行过程中，我们发现当size为5的时候，线程2就输出来内容，但是在实现中，我遇到一个问题，那就是如果没有volatile关键字，那么
        循环是不会暂停的呢，不会出现我们预期的结果，当然，原因就自己思考了
         */
        /**
         * 虽然，我们通过循环实现了我们想要的形式，但是运用循环，很容易出现问题，而且效率太低，就比如刚刚实验遇到的问题，所以我们需要用一种更好的机制
         * 就是我们今天的主角等待／通知技术
         */
//        List list = new ArrayList();
//        StudyWaitAndNotifyFour studyWaitAndNotifyFour = new StudyWaitAndNotifyFour(list);
//        studyWaitAndNotifyFour.setName("b");
//        studyWaitAndNotifyFour.start();
//        try {
//            Thread.sleep(50);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        StudyWaitAndNotifyThree studyWaitAndNotifyThree = new StudyWaitAndNotifyThree(list);
//        studyWaitAndNotifyThree.setName("a");
//        studyWaitAndNotifyThree.start();
        //代码执行过程中，我们发现一个问题，就是wait的那个对象是在最后执行的，需要等待notify的代码块先执行完，但是一开始是线程2执行，
        //在执行wait后，线程一能执行，这就说明了wait和notify的差别，notify不会释放锁，而wait会释放锁

        /**
         * 我们之前也有了解到，在阻塞状态中使用停止interrupt函数，会出现异常，在对wait状态的线程使用，也会报出异常，这里就不演示了
         */


        /**
         * wait（long）函数，wait（）的使用，前者比后者更加灵活一点，可以自动设定时间，如果超过这个时间，那么会自动取消阻塞状态
         */
         StudyWaitAndNotifyFive studyWaitAndNotifyFive=new StudyWaitAndNotifyFive();
         studyWaitAndNotifyFive.start();
         //结果可以发现，两秒后，自动解除了阻塞状态，线程继续运行
    }
}

/**
 * 先介绍一种方法，是可以不用wait和notify技术实现的等待通知，需要利用循环的方法
 */
class StudyWaitAndNotifyFirst extends Thread {
    private volatile List test;

    public StudyWaitAndNotifyFirst(List list) {
        super();
        this.test = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            test.add(i);
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

class StudyWaitAndNotifySecond extends Thread {
    private volatile List list;

    public StudyWaitAndNotifySecond(List list) {
        super();
        this.list = list;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (list.size() == 5) {
                    System.out.println("被终止");
                    throw new Exception();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * 运用等待／通知机制来实现之前的要求
 */

class StudyWaitAndNotifyThree extends Thread {
    private List list;

    public StudyWaitAndNotifyThree(List list) {
        super();
        this.list = list;
    }

    @Override
    public void run() {
        synchronized (list) {
            for (int i = 0; i < 10; i++) {
                list.add(i);
                System.out.println(i);
                try {
                    Thread.sleep(1000);
                    if (list.size() == 5) {
                        list.notify();

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

class StudyWaitAndNotifyFour extends Thread {
    private List list;

    public StudyWaitAndNotifyFour(List list) {
        super();
        this.list = list;
    }

    @Override
    public void run() {
        synchronized (list) {
            if (list.size() != 5) {
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            System.out.println("被终止");
        }
    }
}

/**
 * 验证wait（long）函数的使用
 */
class StudyWaitAndNotifyFive extends Thread{
    @Override
    public void run() {
        synchronized (this) {
            try{
                System.out.println("进入阻塞");
                this.wait(2000);
                System.out.println("阻塞解除");
            }catch (InterruptedException  e){
                e.printStackTrace();
            }
        }
    }
}