import java.lang.reflect.*;

public class Main {

    public static void main(String[] args) {
        //创建接口类型，要生成动态代理的类
        InvocationHandler handler = new MyInvocationHandler();
        //生成动态代理实例（接口类型）
        //参数（生成动态代理的ClassLoader，代理类实现的接口...，要生成动态代理的类）
        Dog d = (Dog)Proxy.newProxyInstance(Dog.class.getClassLoader(),
                new Class[]{Dog.class},handler);
        d.go();
        d.run();
    }
}
