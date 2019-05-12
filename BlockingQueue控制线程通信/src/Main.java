import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws Exception{

        BlockingQueue<String> bq = new ArrayBlockingQueue<>(1);
        new Producer(bq).start();
        new Producer(bq).start();
        new Producer(bq).start();

        //Thread-0,1,2必须等3执行后才会执行
        new Consumer(bq).start();
    }
}
