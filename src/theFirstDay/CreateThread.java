package theFirstDay;



/**
 * Created by zhengjiqi on 2017/5/28.
 */
public class CreateThread {
    /**
     *创建线程的方式：常见的有两种情况：1。继承Thread类，2。实现Runannle接口
     * 其实还有第三种方式，第三种方式是借用框架Executor框架，实现Callable接口
     * */
    public static void main(String[] args)
    {
     TestThread testThread=new TestThread();

        /**
         * 实现第二种情况，第三种情况不实现
         */
     Thread thread=new Thread(new Runnable() {
         @Override
         public void run() {
             System.out.println("第二种线程情况创建");
         }
     });
     testThread.start();
     thread.start();
     System.out.println("main线程");//运行后结果可以发现，并不是主线程运行结束了，子线程就停止运行,而且顺序是不定的哦，
        // 在学习线程就要抛弃以往顺序逻辑思维来。
     //testThread.run();
     /*这里解释一下start函数和run函数的区别，调用线程在两个是常用的方法，run函数只是像平常调用函数
        的情况相似，并不会开启一个线程来进行执行，start函数是开启一个新的线程来执行，会自动调用run函数
     */

    }
}

/**
 * 实现第一种情况
 */
class TestThread extends Thread{
    //覆盖Thread中的run方法，达到自定义线程的功能
    @Override
    public void run(){
        System.out.println("我只是测试");
    }
}
