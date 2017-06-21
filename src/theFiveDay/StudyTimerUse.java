package theFiveDay;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zhengjiqi on 2017/6/20.
 */
public class StudyTimerUse {
    /**
     * 在之前我们已经了解了定时器timer和任务TimerTask之间的关系， 并简单的创建类一个任务类，但是
     * 定时器的使用并没有那么简单，运用方式也不相同，现在就来了解一下
     * 主要使用的是schedule这个函数，也就加载任务的函数
     */

    public static void main(String[] args) {

        /**
         * 首先介绍schedule（TimerTask timerTask，Data time）该函数的使用，这个函数主要是在指定time时间点执行该任务，
         * 因为是人为指定时间，所以会出现三种情况，
         * 1.时间是过去的时间
         * 2.时间是当前时间
         * 3.时间是未来时间
         */


        //测试第一种情况，时间是过去的时间

        //构建一个定时器
//        Timer timer=new Timer();
//        //确定运行时间
//        Calendar calendar=Calendar.getInstance();
//        calendar.set(Calendar.SECOND,calendar.get(Calendar.SECOND)-20);
//        System.out.println("当前时间："+System.currentTimeMillis());
//        timer.schedule(new TaskSecond(),calendar.getTime());
        //运行结果发现，如果时间是过去的时间，则是立即运行，


        //测试第二种情况，时间是当前的时间

//        //构建一个定时器
//        Timer timer=new Timer();
//        //确定运行时间
//        Calendar calendar=Calendar.getInstance();
//        System.out.println("当前时间："+System.currentTimeMillis());
//        timer.schedule(new TaskSecond(),calendar.getTime());
//        //运行结果可以发现，如果时间是当前时间，则会立即执行


        //测试第三种情况,时间是未来的时间

        //构建一个定时器
//        Timer timer=new Timer();
//        //确定运行时间
//        Calendar calendar=Calendar.getInstance();
//        calendar.set(Calendar.SECOND,calendar.get(Calendar.SECOND)+2);
//        System.out.println("当前时间L："+System.currentTimeMillis());
//        timer.schedule(new TaskSecond(),calendar.getTime());
        //运行结果可以发现，如果时间是未来时间，则需要等待时间点到才会执行，也就是会延时，但是不影响主线程运行


        /**
         * 第二个要介绍的是schedule（TimerTask timerTask，Date time，long period）,这个方法相对于前一个而言，就是多了一个
         * 循环的作用，这个会以间隔时间不停的重复执行这个任务,当然这个因为time时间，所以也要分3种情况
         * 1.时间是过去的时间
         * 2.时间是当前时间
         * 3.时间是未来时间
         */

        //测试第一种情况，时间是过去的时间
//        Timer timer = new Timer();
//        //确定运行时间
//        Calendar calendar=Calendar.getInstance();
//        calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND)-20);
//        System.out.println("当前时间："+System.currentTimeMillis());
//        timer.schedule(new TaskSecond(),calendar.getTime(),1000);

        //执行结果可以发现，过去时间会立即执行，而且任务在间隔一秒以后被重复执行，执行间隔为period。



        //测试第二种情况，时间是当前的时间

//        Timer timer = new Timer();
//        //确定运行时间
//        Calendar calendar=Calendar.getInstance();
//        //calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND)-20);
//        System.out.println("当前时间："+System.currentTimeMillis());
//        timer.schedule(new TaskSecond(),calendar.getTime(),1000);
        //运行结果和过去式一样，任务会立即执行，而且任务在间隔1秒后被重复执行，执行时间为period。


        //测试第三种情况，时间是未来时间

        //测试第一种情况，时间是过去的时间
//        Timer timer = new Timer();
//        //确定运行时间
//        Calendar calendar=Calendar.getInstance();
//        calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND)+20);
//        System.out.println("当前时间："+System.currentTimeMillis());
//        timer.schedule(new TaskSecond(),calendar.getTime(),1000);

        //运行结果发现，任务会等待时间点的到来，然后便会循环执行任务，间隔时间为period。


        /**
         * Timer定时器，可以执行多个任务，不只是单独放一个任务
         * 这里要注意的是，由于Timer定时器内部是有着队列锁的，是按照顺序执行的，类似同步的情况，但是并不一样
         */
//        Timer timer=new Timer();
//        //确定运行时间
//        Calendar calendar=Calendar.getInstance();
//        timer.schedule(new TaskSecond(),calendar.getTime());
//        timer.schedule(new TasdThrid(),calendar.getTime());
//         //执行结果可以发现，两个任务是按照顺序执行的，并没有抢占，

        //但是这个时候就出现一个问题，如果一个任务执行时间很长，等待的任务的时间点已经到类，那么会发现什么情况呢？
//        Timer timer=new Timer();
//        //确定运行时间
//        Calendar calendar=Calendar.getInstance();
//        Calendar calendar1=Calendar.getInstance();
//        calendar1.set(Calendar.SECOND,calendar.get(Calendar.SECOND)+2);
//        timer.schedule(new TaskFour(),calendar.getTime());
//        timer.schedule(new TasdThrid(),calendar1.getTime());

        //执行结果可以发现，在第一个任务执行结束后，第二个任务并没有等待，而是立即执行，说明，等待时间是可以被运行时间抵消的，但是依旧需要等待前一个任务执行完


        /**
         * 介绍第三种schedule的用法，那就是schedule(TimerTask timerTask,long delay)，该方法是相对于当前时间而言的，相对于当前时间delay时间后执行任务
         * 用法和前面的相差不大，只是这个线程是相对于当前时间而言
         */

//        Timer timer=new Timer();
//        System.out.println("当前时间："+System.currentTimeMillis());
//        timer.schedule(new TaskSecond(),2000);

        //运行结果可以发现，任务被延迟了两秒，相对于当前时间而言

        /**
         * 第四种方法为schedule（TimerTask timerTask，long delay，long period），该方法是相对于当前时间而言，延迟后delay时间执行，并循环
         * 执行任务，间隔为period。
         */

//        Timer timer=new Timer();
//        System.out.println("当前时间为："+System.currentTimeMillis());
//        timer.schedule(new TaskSecond(),2000,1000);
        //运行结果可以发现，任务被延迟2秒后，执行，并循环执行，间隔为一秒


        /**
         * 最后要介绍的是cancel（）方法，这个方法相对于定时器和任务都有不同的含义
         * 1.定时器timer.cancel()作用是清除定时器里面的全部任务，并销毁线程,一开始我们就利用了这个特性来停止任务的线程
         * 2.任务timerTask.cancel()作用是结束这个任务，在定时器里面清除这个任务
         */

        //验证timerTask.cancel(),我们利用循环定时器来实现
//        Timer timer=new Timer();
//        timer.schedule(new TaskFive(),2000,1000);
        //发现任务只被执行一次，就没有继续循环执行，原因在于该任务已经被消除了，不存在了，但是线程没有被暂停

        //验证timer.cancel（）,同样我们利用循环定时器执行

        Timer timer=new Timer();
        timer.schedule(new TaskSecond(),2000,1000);
        timer.schedule(new TaskSecond(),2000,1000);
        try{
            Thread.sleep(6000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        timer.cancel();
        //运行过程中发现，循环执行后，过了线程延迟的时间，任务停止，而且线程也被销毁了，运行结束。
    }
}

/**
 * 构建一个任务
 */
class TaskSecond extends TimerTask {
    @Override
    public void run() {
        System.out.println("任务执行:" + System.currentTimeMillis());
    }
}

class TasdThrid extends TimerTask{
    @Override
    public void run() {
        System.out.println("第二个任务执行:" + System.currentTimeMillis());
    }
}

class TaskFour extends TimerTask{
    @Override
    public void run() {
        try{
            System.out.println("任务开始执行");
            Thread.sleep(3000);
            System.out.println("任务执行结束"+System.currentTimeMillis());
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class TaskFive extends TimerTask{
    @Override
    public void run() {
        System.out.println("任务被执行");
        System.out.println("任务被销毁");
        this.cancel();

    }
}