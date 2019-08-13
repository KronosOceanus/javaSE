import java.io.*;
import java.util.ArrayList;

//另一种自定义序列化实现Externalizable接口，必须重写它的writeExternal和readExternal方法，性能略高
public class Person implements Serializable {

    //标识该类的序列化版本，修改类的非瞬时实例变量可能导致序列化失败，或者多出来的实例变量为null/0
    private static final long serialVersionUID = 5;

    private String name;
    private int age;

    //不提供无参构造器
    public Person(String name, int age){
        System.out.println("有参构造器");
        this.name = name;
        this.age = age;
    }

    //程序在序列化该对象之前，先调用这两个方法
    //控制序列化返回对象类型
    private Object writeReplace()throws ObjectStreamException{
        ArrayList<Object> list = new ArrayList<>();
        list.add(name);
        list.add(age);
        return list;
    }
    //保存并写入序列化对象的状态
    private void writeObject(ObjectOutputStream out) throws IOException {
        //name实例变量反转后写入二进制流（反转是一种加密方式）
        out.writeObject(new StringBuffer(name).reverse());
        out.writeInt(age);
    }

    //调用readObject方法之后才会调用readResolve方法，如果重写readResolve方法，会立刻返回readResolve方法的对象
    private void readObject(ObjectInputStream in)throws IOException,ClassNotFoundException{
        //将读取的字符串反转后赋值给name变量
        this.name = ((StringBuffer)in.readObject()).reverse().toString();
        this.age = in.readInt();
    }
    /**
     * 序列化单例，枚举类必须提供该方法
     *private Object readResolve() throws ObjectStreamException{
     *     if(value == 1)
     *         return 对象1;
     *     if(value == 2)
     *         return 对象2;
     *}
     * 对应枚举类
     * public enum Value{
     *     对象1(1),对象2(2);
     *     private int value;
     *     private Value(int value){
     *         this.value = value;
     *     }
     * }
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
