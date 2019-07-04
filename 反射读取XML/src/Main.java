import org.dom4j.DocumentException;
import org.dom4j.Element;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public void testReflectAndXML() throws DocumentException,
            ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        Sax sax = new Sax();
        Object target = null;
        for (Element childElement : sax.getChildElements()){
            String childEleName = childElement.getName();
            if ("class".equals(childEleName)){
                //得到类名
                String className = childElement.getText();
                Class<?> clazz = Class.forName(className);
                //得到实例
                target = clazz.getDeclaredConstructor().newInstance();
                for (Field f : clazz.getDeclaredFields()){
                    Method method1 = clazz.getMethod(
                            "setName",String.class);
                    Method method2 = clazz.getMethod(
                            "setAge",Integer.class);
                    method1.invoke(target,"老王");
                    method2.invoke(target,18);
                }
            }
        }
        System.out.println(((Person)target).getName() + " 的年龄是 " +
                ((Person)target).getAge() + " 岁");
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, DocumentException, InvocationTargetException, ClassNotFoundException {
        Main m = new Main();
        m.testReflectAndXML();
    }
}
