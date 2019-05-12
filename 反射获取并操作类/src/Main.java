import java.io.*;
import java.lang.reflect.*;
import java.util.*;

/**
 * 初始化属性文件，对象池，通过属性文件调用方法
 * 要有创建对象，和从对象池中获取对象的方法
 * reflect包下的Array操作数组与操作成员变量相似
 */
public class Main {

    //存放类名，对象的对象池
    private static Map<String,Object> objectPool = new HashMap<>();
    private static Properties props = new Properties();
    //初始化属性文件
    public void init(String fileName){
        try(
                FileInputStream fis = new FileInputStream(fileName);
        ){
            props.load(fis);
        } catch (IOException e) {
            System.out.println("读取 " + fileName + "异常");
        }
    }
    //创建某个类实例
    private Object createObject(String clazzName)
            throws InstantiationException, IllegalAccessException,
            ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException
    {
        //通过无参构造器获取该类实例
        Class<?> clazz = Class.forName(clazzName);
        return clazz.getDeclaredConstructor().newInstance();
    }

    //根据指定文件初始化对象池
    public void initPool()
            throws InstantiationException, IllegalAccessException,
            ClassNotFoundException,NoSuchMethodException,InvocationTargetException
    {
        //得到String类型key的方法
        for(String name : props.stringPropertyNames()){
            if(!name.contains("%")){
                objectPool.put(name,createObject(props.getProperty(name)));
            }
        }
    }

    //根据属性文件调用方法，访问并修改成员变量
    public void transferMethodAndAccessField()
            throws InvocationTargetException, IllegalAccessException,
            NoSuchMethodException, NoSuchFieldException {
        for(String name : props.stringPropertyNames()){
            //在key中，将对象与调用方法名称%分隔
            if(name.contains("%")) {
                String[] objAndProp = name.split("%");
                Object target = getObject(objAndProp[0]);
                //得到方法名称
                String mtdName = "set" + objAndProp[1].substring(0, 1).toUpperCase()
                        + objAndProp[1].substring(1);
                //得到类，设定方法名称与形参得到Method，设定主调与修改值执行invoke
                Class<?> targetClass = target.getClass();
                Method method = targetClass.getMethod(mtdName, String.class);
                method.invoke(target, props.getProperty(name));
                //获取成员变量（非法反射）
                /**
                 Field rootPaneCheckingEnabledField = targetClass.getDeclaredField(
                 "rootPaneCheckingEnabled");
                 //取消访问权限检查
                 rootPaneCheckingEnabledField.setAccessible(true);
                 rootPaneCheckingEnabledField.setBoolean(target,true);
                 */
            }
        }
    }
    //通过String类型的key得到value的方法（get得到value只能通过K类型的key）
    public Object getObject(String name){
        return objectPool.get(name);
    }

    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.init("obj.txt");
        m.initPool();
        m.transferMethodAndAccessField();
        System.out.println(m.getObject("a"));
    }
}