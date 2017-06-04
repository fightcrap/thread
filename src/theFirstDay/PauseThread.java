package theFirstDay;

/**
 * Created by zhengjiqi on 2017/5/31.
 */
public class PauseThread {
    private int i = 0;
    private int j = 0;

    /**
     * 线程取消的情况我们已经学习过了，现在学习下线程的暂停和回复功能，这边介绍两个api，分别为：suspend与resume
     * suspend是暂停，resume是回复
     */
    public static void main(String[] args) {
//        PauseThreadFirst pauseThreadFirst=new PauseThreadFirst();
//        pauseThreadFirst.start();
//        try {
//            Thread.sleep(1000);
//            pauseThreadFirst.suspend();
//            System.out.println(pauseThreadFirst.getI());
//            Thread.sleep(1000);
//            System.out.println(pauseThreadFirst.getI());
//            pauseThreadFirst.resume();
//            Thread.sleep(1000);
//            System.out.println(pauseThreadFirst.getI());
//            pauseThreadFirst.stop();
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//        }

        /**
         * 运用suspend暂停是会占用资源的，也就是线程的锁并没有被释放掉，所以会出现独占的情况，注意 println内部是同步的哦，所以也会出现这个情况的
         */
//        PauseThread pauseThread=new PauseThread();
//         Thread test1=new Thread(){
//                 @Override
//                public void run(){
//                     pauseThread.test();
//        }
//         };
//         test1.start();
//         try {
//             Thread.sleep(5000);
//             Thread thread=new Thread(){
//                 @Override
//                 public void run(){
//                     System.out.println("第二个线程启动，测试能不能获取资源");
//                     pauseThread.test();
//                 }
//             };
//             thread.start();
//
//         }catch (Exception e){
//             e.printStackTrace();
//         }


        /**
         * suspend和resume还有一个问题，那就是不同步的问题，当线程被暂停后，后面的语句没有执行，这时去访问数据时，就会出现不同步的问题
         */
        PauseThread pauseThread=new PauseThread();
        Thread thread = new Thread() {
            @Override
            public void run() {
pauseThread.change();
            }
        };
        try{
            thread.setName("a");
            thread.start();
            Thread.sleep(1000);
            Thread thread1=new Thread(){
                @Override
                public void run(){
                    System.out.println(pauseThread.toString());
                }
            };
            thread1.start();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    synchronized void test() {
        System.out.println("运行了测试方法");
        System.out.println("但是我已经被暂停了");
        Thread.currentThread().suspend();
        System.out.println("结束暂停了");
    }

    public void change() {
        i = 8;
        if (Thread.currentThread().getName().equals("a")) {
            System.out.println("暂停了线程哟");
            Thread.currentThread().suspend();
        }
        j = 10;
    }
    @Override
    public String toString(){

        return i+"    "+j;
    }

}

/**
 * 创建一个线程用来测试api
 */
class PauseThreadFirst extends Thread {
    private long i = 0;

    public long getI() {
        return i;
    }

    public void setI(long i) {
        this.i = i;
    }

    @Override
    public void run() {
        while (true) {
            i++;
        }
    }
}

/**
 * 创建两个线程用于测试独占的情况
 */
class PauseThreadTwo extends Thread {

    @Override
    public void run() {

    }
}