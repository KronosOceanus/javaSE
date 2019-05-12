import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class Main {

    public static void main(String[] args) throws IOException {

        File f = new File("FileChannelTest.java");
        try(
                FileChannel inChannel = new FileInputStream(f).getChannel();
                FileChannel outChannel = new FileOutputStream("a.txt").getChannel();
                )
        {
            //FileChannel全部数据映射成buffer
            MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY,0,f.length());
            //使用GBK字符创建解码器
            Charset charset = Charset.forName("GBK");
            outChannel.write(buffer);
            buffer.clear();
            //创建解码器
            CharsetDecoder decoder = charset.newDecoder();
            //使用解码器转换buffer类型
            CharBuffer charBuffer = decoder.decode(buffer);
            System.out.println("一次读取并输出:\n"+charBuffer);
        }

        //追加数据
        try(
                RandomAccessFile raf = new RandomAccessFile(f,"rw");
                //该Filechannel读写方式取决于RandomAccessFile
                FileChannel randomChannel = raf.getChannel();
                )
        {
            ByteBuffer buffer = randomChannel.map(FileChannel.MapMode.READ_ONLY,0,f.length());
            //Channel指针移动到最后
            randomChannel.position(f.length());
            //输出到原来文件里
            randomChannel.write(buffer);
        }

        //循环读取
        try(
                FileInputStream fis = new FileInputStream("FileChannelTest.java");
                FileChannel fcin = fis.getChannel();
                )
        {
            ByteBuffer bbuff = ByteBuffer.allocate(256);
            System.out.println("追加后多次读取并输出: ");
            while(fcin.read(bbuff) != -1){
                //锁定空白区
                bbuff.flip();
                Charset charset = Charset.forName("GBK");
                CharsetDecoder decoder = charset.newDecoder();
                CharBuffer cbuff = decoder.decode(bbuff);
                System.out.println(cbuff);
                //初始化，为下一次读取做准备
                bbuff.clear();
            }
        }
    }
}
