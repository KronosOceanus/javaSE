import java.lang.reflect.*;

public class MyProxyFactory {

    //为指定的target生成动态代理对象
    // ②
    public static Object getProxy(Object target) throws Exception{
        MyInvocationHandler handler = new MyInvocationHandler();
        //保存真实对象
        handler.setTarget(target);
        //Class对象的getInterfaces方法获取该类实现的所有接口
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),handler);
    }
}
