import java.io.*;

public class Main {

    public static void main(String[] args)throws IOException {

        //获得运行该命令的子进程
        Process p = Runtime.getRuntime().exec("javac");
        try(
                //数据从p进程输出到本程序
                //该错误流对于本程序是输入流，对p进程是输出流（数据进/出哪，对哪就是输入/出流）
                BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        )
        {
            String buff = null;
            while((buff = br.readLine()) != null){
                System.out.println(buff);
            }
        }
    }
}