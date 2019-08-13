
/**
 * AOP 可以方便实现代码解耦
 * 本程序主要用于DogUtil类代码的复用
 *
 * 定义接口，实现类，复用代码类（调用接口动态代理对象的方，实现代码复用）
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Dog target = new GunDog();
        Dog proxy = (Dog)MyProxyFactory.getProxy(target); // ①（执行过程）
        proxy.run(); // ③
        proxy.go();
    }
}
