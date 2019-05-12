import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws Exception{

        //不连续打印1~300
        ForkJoinPool pool = new ForkJoinPool();
        //提交“可分解”任务，ForkJoinPool启动8个线程（cpu核数）
        pool.submit(new PrintTask(0,300));
        //在关闭请求前，完成任务执行，与awaitQuiescence方法相同，但总是返回false
        pool.awaitTermination(2, TimeUnit.SECONDS);


        //输出1-100之和
        int[] arr = new int[100];
        Random rand = new Random();
        int total = 0;
        for(int i=0;i<100;i++){
            //生成一个0~20的随机数
            int tmp = rand.nextInt(20);
            //单线程测试相加结果
            total += (arr[i] = tmp);
        }
        System.out.println("单线程相加结果: " + total);
        Future<Integer> future = pool.submit(new CalTask(arr,0,arr.length));
        System.out.println("多线程相加结果; " + future.get());

        pool.shutdown();
    }
}
