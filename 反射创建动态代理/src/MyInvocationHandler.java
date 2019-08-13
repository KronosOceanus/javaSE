import java.lang.reflect.*;

public class MyInvocationHandler implements InvocationHandler {

    /**
     * 动态代理对象的所有方法，都在该invoke方法中执行
     * 参数自动生成（代理对象，方法，参数数组）
     * ③ ⑤
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("正在执行的方法：" + method);
        if(args != null){
            System.out.println("执行方法时传入的参数：");
            for(Object val : args){
                System.out.println(val);
            }
        }else{
            System.out.println("该方法没有实参！");
        }
        return null;
    }
}
