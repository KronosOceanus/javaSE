public class Main {

    public static void main(String[] args) {

        //同步机制在共享资源的同时还可以进行通信
        Account acct = new Account("初始名");
        new MyTest(acct,"线程A").start();
        new MyTest(acct,"线程B").start();
    }
}
