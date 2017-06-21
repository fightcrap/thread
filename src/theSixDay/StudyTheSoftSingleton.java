package theSixDay;

/**
 * Created by zhengjiqi on 2017/6/21.
 */
public class StudyTheSoftSingleton {

    /**
     * 解决多线程问题，我们第一个想到的是就是我们最熟悉的synchronized关键字，同步的方式，还有lock锁等等，这些都是我们
     * 最熟悉的，但是相对于效率而言，如果只是套用简单的同步形式，那么效率将会不高。现在介绍一种Dcl双检测锁机制，虽然这个
     * 用的是synchronized关键字，但是效率会提高
     */

    public static void main(String[] args){

        ThreadFour threadFour=new ThreadFour();
        ThreadFour threadFour1=new ThreadFour();
        ThreadFour threadFour2=new ThreadFour();
        threadFour.start();
        threadFour1.start();
        threadFour2.start();
        //单例模式的效果出现了，相对于以前的方法更加好一点
    }


}
class ThreadDCLStudy{
    private static volatile Object object; //运用volatile关键字来保证线程可见性
    private ThreadDCLStudy(){

    }
    public static Object get(){
        //通过延迟来模拟长时间的运行，这样使用synchronized方法的效率不高
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        if(object==null){
            synchronized ("w"){
                //直接使用synchronized代码块，起到作用是一样的，

                //所以需要DCL双锁机制,为什么叫双锁，原因在于多一层判断
                if(object==null){
                    object=new Object();
                }
            }

        }
            return object;
    }
}


class ThreadFour extends Thread{
    @Override
    public void run(){
        System.out.println(ThreadDCLStudy.get().hashCode());
    }
}