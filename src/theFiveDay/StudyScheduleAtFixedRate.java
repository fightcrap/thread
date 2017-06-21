package theFiveDay;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zhengjiqi on 2017/6/20.
 */
public class StudyScheduleAtFixedRate {
    /**
     * 介绍完了schedule的几种使用方式，但是还有另外一种形式，我们一直没有介绍过，那就是scheduleAtFixedRate，这个和
     * schedule作用差不多，相差不大，区别在于scheduleAtFixedRate具有追赶性，而schedule却没有.
     */

    public static void main(String[] args){
        /**
         * scheduleAtFixedRate也有两个重载方法，分别是scheduleAtFixedRate（TimerTask timerTask，Data time,long period）和
         * scheduleAtFixedRate（TimerTask timerTask，long delay,long period）运行过程和schedule差不多，差别在于
         * 当time 是过去时间的时候，具有追补性的scheduleAtFixedRate会补充性的执行，弥补过去的时间，而schedule不会。
         */
        Timer timer=new Timer();
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.SECOND,calendar.get(Calendar.SECOND)-20);
        System.out.println("当前时间："+System.currentTimeMillis());
       // timer.schedule(new TaskSix(),calendar.getTime(),1000);

        //运行发现，schedule是立即执行任务的，并没有追赶以前的时间



        timer.scheduleAtFixedRate(new TaskSix(),calendar.getTime(),1000);
        //运行结果发现，程序会瞬间执行过去时间差距的次数，瞬间执行，  然后在按照正常的进行执行,这就是追赶性

    }
}

class TaskSix extends TimerTask{
    @Override
    public void run() {
        System.out.println("程序运行:"+System.currentTimeMillis());
    }
}
