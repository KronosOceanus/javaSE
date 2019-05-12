public class Main {

    public static void main(String[] args) {

        //存取钱4次（实际2次），if，else也算一次
        Account acct = new Account("1234567",0);
        new DrawThread("取钱者",acct,800).start();
        new DepositThread("存款者A",acct,800).start();


        /**
            //存钱12次，取钱4次，所以程序被阻塞（不是死锁）
            new DepositThread("存款者B",acct,800).start();
            new DepositThread("存款者C",acct,800).start();
         */
    }
}
