public class Main {

    public static void main(String[] args) {
        Account acct = new Account("1234567",1000);
        new DrawThread("A",acct,800).start();
        new DrawThread("B",acct,800).start();

        //两个对象互相调用对方同步方法可能会导致死锁（双锁）
    }
}
