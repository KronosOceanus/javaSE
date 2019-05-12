import java.io.*;
import java.nio.*;

public class Main {

    //四个标记位置关系0<=mark<=position<=limit<=capacity
    public static void main(String[] args) throws IOException {

        //alolocate(int)方法用来获得普通Buffer对象
        //ByteBuffer提供allocateDirect方法创建直接Buffer，成本高，效率也高
        CharBuffer buff =CharBuffer.allocate(8);
        System.out.println("capacity: "+buff.capacity());
        System.out.println("limit: "+buff.limit());
        System.out.println("position: "+buff.position());

        //放入元素
        buff.put('a');
        buff.put('b');
        buff.put('c');
        System.out.println("加入三个元素后position: "+buff.position());

        buff.flip();
        System.out.println("调用filp方法后 limit: "+buff.limit());
        System.out.println("position: "+buff.position());

        //取出第一个元素
        System.out.println("第一个元素（position=0）: "+buff.get());
        System.out.println("取出后position: "+buff.position());

        buff.clear();
        System.out.println("执行clear方法后 limit: "+buff.limit());
        System.out.println("position: "+buff.position());
        System.out.println("执行clear方法后缓冲区没有被清除,第三个元素为: "+buff.get(2));
        System.out.println("执行绝对读取后,position： "+buff.position());
    }
}
