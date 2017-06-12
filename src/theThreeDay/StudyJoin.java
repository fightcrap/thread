package theThreeDay;

/**
 * Created by zhengjiqi on 2017/6/11.
 */
public class StudyJoin {
    /**
     * 学习了线程的通信，但是我们还是有一些问题，先拿一个例子示范一下
     */
    public static void main(String[] args) {
//        Thread thread = new Thread() {
//            @Override
//            public void run() {
//                System.out.println("线程开始运行咯");
//                try {
//                    Thread.sleep(6000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("线程结束了");
//            }
//        };
//        thread.start();
        //Thread.sleep(?);需要等待多久才可以等待线程执行完？
        //这个时候就要借用join函数了，

//        try{
//            thread.join();
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
//        System.out.println("终于等到你");

        //利用join函数可以使得主线程等待线程执行完后才执行，这就达到了我们所需要的情况了

        /**
         * join的内部结构是利用wait函数来实现等待的，所以在join操作后，使用暂停函数，是会抛出异常的
         */
//        try {
//            Thread thread1 = new Thread() {
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(1000);
//                        thread.interrupt();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            };
//            thread1.start();
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("终于等到你");

        /**
         *join是用wait来实现等待的，所以我们也可以设定等待的时间，如果超出这个时间，那么主线程将会继续执行下去
         */
//        try {
//            thread.join(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("终于等到你");


        /**
         * 接下来介绍一下join中的坑，如果我们查看源代码，我们会发现join也是一个同步的方法，那么就可能出现同步抢锁的问题
         * 抢到锁的不同顺序，结果也会不同
         */
        try {
            Thread13 thread13 = new Thread13();
            Thread12 thread12 = new Thread12(thread13);
            thread12.start();
            thread13.start();
            thread13.join(1000);
            System.out.println("end：" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //运行过程中，我们发现，有时候join后面的语句会在中间的时候被执行，为什么会这样呢，原因在于，join也是同步函数，也会去抢夺锁
        //如果join先被抢到，那么join会先执行，等待时间过去以后，锁就被释了，就可以继续执行主线程的内容了
    }
}


/**
 * 构建线程
 */

class Thread12 extends Thread {
    private Thread13 thread13;

    public Thread12(Thread13 thread13) {
        this.thread13 = thread13;
    }

    @Override
    public void run() {
        synchronized (thread13) {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.println("执行方法一");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class Thread13 extends Thread {


    @Override
    public synchronized void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
                System.out.println("执行方法二");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}