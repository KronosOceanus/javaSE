import java.util.concurrent.locks.*;

public class Printer {

    private final Lock lock = new ReentrantLock();
    private final Condition cond = lock.newCondition();

    //n表示要打印的内容（形参个数可变）
    public void PrintInt(int... n){

        //不用flag和if，else，因为if，else各算一次循环
        try {
            lock.lock();
            //形参个数可变以数组接收
            for (int i=0;i<n.length;i++) {
                System.out.println(Thread.currentThread().getName() + "打印: " + n[i]);
            }

            //先唤醒在这个lock上等待的线程再让该线程休眠
            cond.signalAll();
            cond.awaitNanos(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void PrintChar(char n){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "打印: " + n);

            cond.signalAll();
            cond.awaitNanos(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
