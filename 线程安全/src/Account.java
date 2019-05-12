import java.util.concurrent.locks.ReentrantLock;

//账户
public class Account {

    //定义锁对象
    private final ReentrantLock lock = new ReentrantLock();

    private String accountNo;
    private double balance;
    public Account(){};
    public Account(String accountNo,double balance){
        this.accountNo = accountNo;
        this.balance = balance;
    }

    //同步锁写法
    public void draw(double drawAmount){
        lock.lock();
        try
        {
            if (balance >= drawAmount) {
                System.out.println(Thread.currentThread().getName()+" 取钱成功: " + drawAmount);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                balance -= drawAmount;
                System.out.println("\t余额为: " + balance);
            }
            else {
                System.out.println(Thread.currentThread().getName()+" 取钱失败，余额不足！");
            }
        }
        finally {
            //多个锁时，释放顺序与加锁时相反，释放范围与加锁时相同
            lock.unlock();
        }
    }
    /**
        //同步方法写法（同步监视器是this）
        public synchronized void draw(double drawAmount){
            if (balance >= drawAmount){
                System.out.println("取钱成功: " + drawAmount);
                try{
                    Thread.sleep(1);
                }
                catch(InterruptedException ex){
                    ex.printStackTrace();
                }
                balance -= drawAmount;
                System.out.println("\t余额为: " + balance);
            }
             else {
             System.out.println("取钱失败，余额不足！");
            }
        }
     */

    //根据accountNo重写
    public int hashCode(){
        return accountNo.hashCode();
    }
    public boolean equals(Object obj){
        if (this == null)
            return true;
        if(obj != null && obj.getClass() == Account.class){
            Account target = (Account)obj;
            return target.getAccountNo().equals(accountNo);
        }
        return false;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
    public String getAccountNo() {
        return accountNo;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public double getBalance() {
        return balance;
    }
}
