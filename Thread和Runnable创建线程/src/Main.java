public class Main extends Thread implements Runnable{

    //三个线程交替运行
    private int i;
    public void run(){
        for(;i<100;i++){
            //继承Thread类时可直接使用getName方法获得线程名称（新建的线程）
            //使用Runnable创建线程则要用Thread.currentThread方法获得线程名称
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
    public static void main(String[] args) {
        for (int i=0;i<100;i++){
            //获得线程名称（主线程）
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 20){
                //用Thread创建两个线程
                new Main().start();
                new Main().start();

                //用Runnable创建两个线程
                Main m = new Main();
                new Thread(m,"新线程1").start();
                new Thread(m,"新线程2").start();
            }
        }
    }
}