import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Main {

    public static void copyFolder(File src,File dest) throws IOException{

        //文件夹复制
        if(src.isDirectory()){
            if (! dest.exists()){
                //创建路径
                dest.mkdir();
            }
            //遍历该File下的文件
            String files[] = src.list();
            for(String file:files){
                //参数（文件，路径名）
                File srcFile = new File(src,file);
                File destFile = new File(dest,file);
                //递归复制
                copyFolder(srcFile,destFile);
            }
        }
        //单个文件复制
        else{
            try(
                    FileChannel in = new FileInputStream(src).getChannel();
                    FileChannel out = new FileOutputStream(dest).getChannel();
            )
            {
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                while((in.read(buffer)) != -1){
                    buffer.flip();
                    out.write(buffer);
                    buffer.clear();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        File src = new File("D:\\AndroidProject");
        File dest = new File("D:\\project");
        Main.copyFolder(src,dest);
    }
}
