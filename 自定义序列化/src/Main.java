import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        try (
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("transient.txt"));
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("transient.txt"));
        )
        {
            Person po = new Person("孙悟空",500);
            //反序列化必须要有对应的class文件
            Person pi = null;
            oos.writeObject(po);
            try{
                //反序列化读取的实际是一个ArrayList类型对象（由Replace方法返回）
                ArrayList list = (ArrayList) ois.readObject();
                System.out.println(list);
            }
            catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
