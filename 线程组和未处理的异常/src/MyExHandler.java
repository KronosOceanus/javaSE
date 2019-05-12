//定义自己的异常处理器
public class MyExHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(t + "线程出现了异常: " + e);
    }
}
