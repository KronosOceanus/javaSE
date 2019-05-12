import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.*;

/**
 *
 */
public class ObjectFactoryPool{

    //对象池
    private static Map<String,Object> objectPool = new HashMap<>();

    /**
     * 本例没有用到读取
        Properties props = new Properties();

        //初始化属性
        public void initProps(String fileName){
            try(
                    FileInputStream fis = new FileInputStream(fileName);
                    ){
                props.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //根据类名创建对象
        private Object createObject(String name) throws Exception{
            Class<?> clazz = Class.forName(name);
            return clazz.getDeclaredConstructor().newInstance();
        }

        //初始化对象池
        public void initPool()throws Exception{
            for(String name : props.stringPropertyNames()){
                objectPool.put(name,createObject(props.getProperty(name)));
            }
        }

        //通过名字获取对象
        public Object getObject(String name){
            return objectPool.get(name);
        }
     */

    //修改成员变量的内容
    public static <T> void modifyField(Object obj,String fieldName,T val)
            throws NoSuchFieldException, IllegalAccessException
    {
        Field clazzField = obj.getClass().getDeclaredField(fieldName);
        clazzField.setAccessible(true);
        clazzField.set(obj,val);
    }

    //修改成员变量引用，参数（对象（将src该成员变量的引用指向dest），成员变量名称）
    public static void modifyReference(Object src,Object dest,String fieldName)
            throws NoSuchFieldException, IllegalAccessException
    {
        Field clazzField = src.getClass().getDeclaredField(fieldName);
        clazzField.setAccessible(true);
        clazzField.set(src,dest.getClass().getDeclaredField(fieldName).get(dest));
    }
}