import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        //节点流，使用繁琐
        try(
                //用于读取文件的输入流（直接与文件相关联）
                FileInputStream fis = new FileInputStream("FileInputStreamTest.java");
                //输出流自动新建文件
                FileOutputStream fos = new FileOutputStream("newFile.txt");
        ) {
            byte[] bbuf = new byte[1024];
            //用于保存实际读取的字节数
            int hasRead = 0;
            //通过>0判断是否读取完毕，从输入流中最多读取1024bytes的数据并储存在其中
            while ((hasRead = fis.read(bbuf)) > 0) {
                fos.write(bbuf,0,hasRead);
                //将字节数组转换为字符串，参数（字节数组，起始位置，内容长度）
                System.out.println(new String(bbuf, 0, hasRead));
            }
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }


        try(
                FileReader fr = new FileReader("FileInputStreamTest.java");
                FileWriter fw = new FileWriter("newWFile.txt");
        )
        {
            char[] cbuf = new char[32];
            int hasRead2 = 0;
            while((hasRead2 = fr.read(cbuf)) > 0)
            {
                System.out.println(new String(cbuf,0,hasRead2));
            }
            //字符串直接输入
            fw.write("//yeah",0,4);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }

        //处理流基本使用
        try(
                FileOutputStream fos = new FileOutputStream("newPackFile.txt");
                PrintStream ps = new PrintStream(fos);
        )
        {
            ps.println("处理流");
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
