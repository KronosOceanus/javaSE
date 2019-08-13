/**
 * ThreadLocal 类，本质是一个数组（在数据库连接池中使用，用于控制事务）
 * 为每个成员变量创建一个副本，每个线程有自己的成员变量副本
 *
 */
public class Main {

    public static void main(String[] args) {

        //同步机制在共享资源的同时还可以进行通信
        Account acct = new Account("初始名");
        new MyTest(acct,"线程A").start();
        new MyTest(acct,"线程B").start();
    }
}
