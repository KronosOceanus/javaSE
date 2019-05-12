import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException{

        //保存默认标准输出流
        PrintStream out = System.out;
        try(
                //输出流会自动新建文件
                PrintStream ps = new PrintStream(new FileOutputStream("out.txt"));
                )
        {
            //将标准输出重定向到ps输出流
            System.setOut(ps);
            System.out.println("字符串");
            //向标准输出输出一个对象
            System.out.println(new Main());
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }

        try(
                //输入需要手动新建
                FileInputStream fis = new FileInputStream("Test.java");
        )
        {
            //将标准输入重定向到fis输入流
            System.setIn(fis);
            //使标准输出流恢复
            System.setOut(out);
            Scanner sc = new Scanner(System.in);
            sc.useDelimiter("\n");
            while(sc.hasNext()){
                System.out.println(sc.next());
            }
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
        out.close();
    }
}
