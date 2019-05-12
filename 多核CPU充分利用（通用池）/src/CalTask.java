import java.util.concurrent.RecursiveTask;

//继承该类，实现有返回值的“可分解”任务，返回值类型为T，线程执行体为compute
public class CalTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 20;
    private int[] arr;
    private int start;
    private int end;
    public CalTask(int[] arr,int start,int end){
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    //累加start~end的元素（递归）
    protected Integer compute() {
        int sum = 0;
        if(end - start < THRESHOLD){
            for(int i=start;i<end;i++){
                sum += arr[i];
            }
            return sum;
        }
        else {
            int middle = (start + end)/2;
            CalTask left = new CalTask(arr,start,middle);
            CalTask right = new CalTask(arr,middle,end);
            left.fork();
            right.fork();
            //等待线程相加完毕
            return left.join() + right.join();
            //相当于return left + right;
            //      left.join();
            //      right.join();
        }
    }
}
