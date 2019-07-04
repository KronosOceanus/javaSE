package main;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 数据转换类
 * 为数据表对应的数据赋值
 */
public class DataUtil {

    public static Collection getData(Collection result, ResultSet rs,Class clazz)
            throws Exception {
        while(rs.next()){
            //新建数据表实体类对象
            Object ov = clazz.getDeclaredConstructor().newInstance();
            //得到所有！！！成员变量
            Field[] fields = clazz.getDeclaredFields();
            Field[] superFields = clazz.getSuperclass().getDeclaredFields();
            Field[] allFields = addFields(fields,superFields);
            for (Field f : allFields){
                //判断是否为引用类型，不是则执行setter
                if(f.getType() == String.class){
                    Method setterMethod = clazz.getMethod(getSetterMethodName(f.getName()),
                            f.getType());
                    invokeMethod(rs,f,ov,setterMethod);
                }
            }
            result.add(ov);
        }
        return  result;
    }

    //从ResultSet中获取一个字段的数据，并调用ov的setter方法设置变量值
    //参数（结果集/数据，变量/变量名称，对象/主调，方法/setter）
    private static void invokeMethod(ResultSet rs,Field field,
                     Object ov,Method setterMethod) throws Exception {
        //数据
        String value = rs.getString(field.getName());
        setterMethod.invoke(ov,value);
    }
    //得到成员变量的Setter方法名
    private static String getSetterMethodName(String fieldName){
        //首字母大写
        String begin = fieldName.substring(0,1).toUpperCase();
        String end = fieldName.substring(1);
        String result = "set" + begin + end;
        return result;
    }

    //数组相加的方法
    private static Field[] addFields(Field[] f1,Field[] f2){
        List<Field> l = new LinkedList<>();
        Collections.addAll(l, f1);
        Collections.addAll(l, f2);
        return l.toArray(new Field[f1.length + f2.length]);
    }





    //测试
    public static void main(String[] args) {

    }
}
