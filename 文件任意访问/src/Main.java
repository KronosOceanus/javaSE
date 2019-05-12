import java.io.*;

public class Main {

    public static void main(String[] args) {

        //任意读取
        try(
                RandomAccessFile raf = new RandomAccessFile("Test.java","r");
                )
        {
            //获取文件指针位置
            System.out.println("文件指针初始位置 "+raf.getFilePointer());
            raf.seek(3);
            byte[] bbuf = new byte[1024];
            int hasRead = 0;
            while((hasRead = raf.read(bbuf)) > 0){
                System.out.println(new String(bbuf,0,hasRead));
            }
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }

        //追加内容
        try(
                //读写文件也是自动创建
                RandomAccessFile raf = new RandomAccessFile("out.txt","rw");
                )
        {
            //文件内容长度
            raf.seek(raf.length());
            //getBytes方法将字符串转换为Byte[]
            raf.write("追加的内容！\r\n".getBytes());
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
