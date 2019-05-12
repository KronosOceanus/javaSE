public class Main{

    public static void main(String[] args) {

        //设置主线程的异常处理器
        Thread.currentThread().setUncaughtExceptionHandler(new MyExHandler());

        //ThreadGroup实现了Thread.UncaughtExceptionHandler接口，线程组将会作为异常处理器
        //获取主线程的线程组
        ThreadGroup mainThread = Thread.currentThread().getThreadGroup();
        System.out.println("主线程组的名字: " + mainThread.getName());
        System.out.println("主线程组是否是后台线程组: " + mainThread.isDaemon());

        new Thread("主线程组的线程").start();
        ThreadGroup tg = new ThreadGroup("新线程组");
        tg.setDaemon(true);
        System.out.println("tg线程组是否是后台线程组: " + tg.isDaemon());
        new MyThread(tg,"tg组的线程A").start();
        new MyThread(tg,"tg组的线程B").start();

        //异常处理器作用
        int a = 5 / 0;
        System.out.println("程序正常结束");
    }
}
