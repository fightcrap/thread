package theFiveDay;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zhengjiqi on 2017/6/19.
 */
public class StudyTimerAndTimerTask {
    /**
     * 学习了多线程的基本内容，现在就来了解一些多线程的应用，最常见的就是我们的定时器功能，类似于多线程，但是和多线程却不同
     */
    public static void main(String[] args) {
        /**
         * 初识定时器和任务，我们先来简单的创建一个任务运行
         */
        //构建一个定时器

        Timer timer = new Timer();

        //确定运行时间
        Calendar calendar = Calendar.getInstance();
        //延时2秒
        calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + 2);
        //添加任务到定时器中
        System.out.println("当前时间：" + System.currentTimeMillis());

        timer.schedule(new TaskFirst(), calendar.getTime());

        //这样就是简单的创建类一个任务，但是在运行过程中发现，运行不会停止，原因在于使用定时器，如果不主动停止，那么是不会停止和销毁线程的
        //所以我们可以优化一下
        //利用cancel函数，这个函数后面会讲到
//        Timer timer1 = new Timer();
//        timer1.schedule(new TaskFirst(timer1), calendar.getTime());
        //运行线程被销毁
    }
}

/**
 * 构建一个任务，构建形式和线程方法差不多，继承TimerTask类，并实现run函数
 */
class TaskFirst extends TimerTask {
    //利用传递timer，并用timer。cancel进行清空任务和销毁线程
    private Timer timer;

    public TaskFirst(Timer timer) {
        super();
        this.timer = timer;
    }

    public TaskFirst() {
    }

    @Override
    public void run() {
        System.out.println("任务执行:" + System.currentTimeMillis());
        this.timer.cancel();
    }
}