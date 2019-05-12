import java.io.*;

public class Main {

    public static void main(String[] args){
        try(
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.txt"));
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.txt"));
                )
        {
            Person po = new Person("孙悟空",500);
            //将对象写入输出流
            oos.writeObject(po);

            //读取对象（如果多个对象就需要按写入顺序读取）
            Person pi = null;
            //可能发生的异常
            try {
                pi = (Person)ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println(pi.getName()+" "+ pi.getAge());
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
