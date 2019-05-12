import java.io.*;

public class Main {

    //参数（文件名，插入点，插入内容）
    public static void insert(String fileName,long pos,String insertContent) throws IOException{
        //创建一个临时文件，参数（前缀名（不少于三个字节），后缀名（可以为null，此时默认为.tmp））
        File tmp = File.createTempFile("tmp",null);
        tmp.deleteOnExit();
        try(
                RandomAccessFile raf = new RandomAccessFile(fileName,"rw");
                //输入输出流和文件直接关联
                FileOutputStream tmpOut = new FileOutputStream(tmp);
                FileInputStream tmpIn = new FileInputStream(tmp);
                )
        {
            //移动到插入点
            raf.seek(pos);
            byte[] bbuf = new byte[64];
            int hasRead = 0;
            //读取插入点后的数据
            while((hasRead = raf.read(bbuf)) > 0){
                //将读取到的数据写入临时文件
                tmpOut.write(bbuf,0,hasRead);
            }
            raf.seek(pos);
            raf.write(insertContent.getBytes());
            //追加临时文件的内容
            while((hasRead = raf.read(bbuf)) > 0){
                raf.write(bbuf,0,hasRead);
            }
        }
    }
    public static void main(String[] args) throws IOException{
        insert("InsertContent.java",45,"插入的内容！\r\n");
    }
}
