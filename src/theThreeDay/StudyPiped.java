package theThreeDay;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created by zhengjiqi on 2017/6/11.
 */
public class StudyPiped {
    /**
     * 进行通信的方法不止wait／notify机制，我们也可以利用管道的方向进行通信，
     * 利用管道通信有两种方式：1。字符流PipedWrite和PipedReader  2字节流PipeInputStream和PipeOutputStream
     */
    public static void main(String[] args){
        PipedInputStream pipedInputStream=new PipedInputStream();
        PipedOutputStream pipedOutputStream=new PipedOutputStream();
        try{
            pipedInputStream.connect(pipedOutputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
        PipedOut pipedOut=new PipedOut(pipedOutputStream);
        PipedIn pipedIn=new PipedIn(pipedInputStream);
        ThreadPipedOut threadPipedOut=new ThreadPipedOut(pipedOut);
        ThreadPipedIn threadPipedIn=new ThreadPipedIn(pipedIn);
        threadPipedIn.start();
        threadPipedOut.start();
        //字符流写法类似，关键是要进行管道链接
    }
}

/**
 * 构建两个类，来实现管道通知
 */
class PipedOut {
    private PipedOutputStream pipedOutputStream;
    public PipedOut(PipedOutputStream pipedOutputStream){
        super();
        this.pipedOutputStream=pipedOutputStream;
    }

   public void out(){
        try {
            System.out.println("write:");
            for (int i = 0; i < 100; i++) {
                pipedOutputStream.write(("" + i).getBytes());
                System.out.print(i);
            }
            System.out.println();
            pipedOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
   }
}
class PipedIn{
    private PipedInputStream pipedInputStream;
    public PipedIn(PipedInputStream pipedInputStream){
        this.pipedInputStream=pipedInputStream;
    }
    public void in(){
        System.out.println("read:");
        byte[] a=new byte[20];
        try {
            int i = pipedInputStream.read(a);
            while(i!=-1){
                System.out.println(new String(a,0,i));
                i=pipedInputStream.read(a);
            }
            pipedInputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
/**
 * 构建线程
 */

class ThreadPipedIn extends Thread{
    private PipedIn pipedIn;
    public ThreadPipedIn(PipedIn pipedIn){
        this.pipedIn=pipedIn;
    }
    @Override
    public void run(){
        pipedIn.in();
    }
}
class  ThreadPipedOut extends Thread{
    private PipedOut pipedOut;
    public ThreadPipedOut(PipedOut pipedOut){
        this.pipedOut=pipedOut;
    }
    @Override
    public void run(){
        pipedOut.out();
    }
}