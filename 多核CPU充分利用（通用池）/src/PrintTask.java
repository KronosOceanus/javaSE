import java.util.concurrent.RecursiveAction;

//继承该类，实现无返回值的“可分解”任务，线程执行体为compute
public class PrintTask extends RecursiveAction {

    //每个任务最多打印数
    private static final int THRESHOLD = 50;
    private int start;
    private int end;
    public PrintTask(int start,int end){
        this.start = start;
        this.end = end;
    }

    @Override
    //打印start~end的元素
    protected void compute() {

        //打印数少于50，直接打印
        if(end - start < THRESHOLD){
            for(int i=start;i<end;i++){
                System.out.println(Thread.currentThread().getName() + "的i值" + i);
            }
        }
        //否则分解打印
        else {
            int middle = (start + end)/2;
            PrintTask left = new PrintTask(start,middle);
            PrintTask right = new PrintTask(middle,end);
            //并行执行两个小任务
            left.fork();
            right.fork();
        }
    }
}
