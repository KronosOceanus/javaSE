import java.util.concurrent.locks.*;

public class Account {

    private final Lock lock = new ReentrantLock();
    private final Condition cond = lock.newCondition();

    private String AccountNo;
    private double balance;
    //存款标记
    private boolean flag = false;
    public Account(){};
    public Account(String AccountNo,double balance){
        this.AccountNo = AccountNo;
        this.balance = balance;
    }
    //省略AccountNo，setter、getter方法
    //账户余额不允许随意修改，只为banlance提供getter方法
    public double getBalance(){
        return this.balance;
    }

    //同步锁写法
    public void draw(double drawAmount){
        try{
            lock.lock();
            if(! flag){
                cond.await();
            }
            else {
                System.out.println(Thread.currentThread().getName()+ " 取钱: " + drawAmount);
                balance -= drawAmount;
                System.out.println("账户余额: " + balance);

                flag = false;
                cond.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void deposit(double depositAmount){
        try{
            lock.lock();
            if (flag){
                cond.await();
            }
            else {
                System.out.println(Thread.currentThread().getName() + "存款: " + depositAmount);
                balance += depositAmount;
                System.out.println("账户余额: " + balance);

                flag = true;
                cond.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    /**
        //同步方法写法
        //wait，notify方法由同步监视器对象调用
        //取钱
        public synchronized void draw(double drawAmount){
            try{
                //flag为假，账户中没有钱，取钱方法阻塞
                if(! flag){
                    wait();
                }
                else {
                    System.out.println(Thread.currentThread().getName()+ " 取钱: " + drawAmount);
                    balance -= drawAmount;
                    System.out.println("账户余额: " + balance);

                    //取钱之后，账户中没有钱，唤醒其他线程
                    flag = false;
                    notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //存款
        public synchronized void deposit(double depositAmount){
            try{
                //flag为真，账户中有钱，存钱方法阻塞
                if (flag){
                    wait();
                }
                else {
                    System.out.println(Thread.currentThread().getName() + "存款: " + depositAmount);
                    balance += depositAmount;
                    System.out.println("账户余额: " + balance);

                    //存钱之后，账户中有钱，唤醒其他线程
                    flag = true;
                    notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    */

    //省略hashCode和equals方法
}
