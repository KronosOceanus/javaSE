import java.lang.reflect.*;

//每个代理对应一个InvocationHandler对象
public class MyInvocationHandler implements InvocationHandler {

    //需要被代理的对象
    private Object target;
    public void setTarget(Object target){
        this.target = target;
    }

    //执行代理对象的方法，都会被替换为（InvocationHandler中的）invoke
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        DogUtil du = new DogUtil();
        du.method1();
        //回调（执行target对象的原有方法），利用反射调用方法
        Object result = method.invoke(target,args);
        du.method2();
        return result;
    }
}
