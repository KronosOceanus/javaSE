import java.io.*;

public class Main {

    public static void main(String[] args) {

        try(
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.txt"));
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.txt"));
                )
        {
            Person po = new Person("孙悟空",500);
            Teacher to1 = new Teacher("唐僧",po);
            Teacher to2 = new Teacher("菩提",po);

            //只有该对象第一次序列化，才会转化为字节顺序输出，如果已经序列化过，则输出时会转换为一个编号
            oos.writeObject(to1);
            oos.writeObject(to2);
            oos.writeObject(po);
            //第二次序列化，该对象没有被写入
            to2.setName("师傅！");
            oos.writeObject(to2);

            //反序列化，必须顺序读取
            Teacher ti1 = null;
            Teacher ti2 = null;
            Person pi = null;
            Teacher ti3 = null;
            try{
                ti1 = (Teacher) ois.readObject();
                ti2 = (Teacher) ois.readObject();
                pi = (Person) ois.readObject();
                ti3 = (Teacher) ois.readObject();

                System.out.println("ti1的引用和pi是否相同 "+(ti1.getStudent() == pi));
                System.out.println("ti2的引用和pi是否相同 "+(ti2.getStudent() == pi));
                System.out.println("ti2和ti3是否是同一个对象 "+(ti2 == ti3));
                //读取到的信息不变
                System.out.println(ti3.getName());
            }
            catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
}
