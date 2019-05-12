import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        Process p = Runtime.getRuntime().exec("java ReadStandard");
        try(
                //对本程序来说是输出流
                PrintStream ps = new PrintStream(p.getOutputStream());
                )
        {
            //向ReadStandard程序写入内容
            //write方法不会写入换行符
            ps.println("普通字符串");
            ps.println(new Main());
        }
    }
}


