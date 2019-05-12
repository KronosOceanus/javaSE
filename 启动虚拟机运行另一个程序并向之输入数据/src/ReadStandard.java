import java.io.*;
import java.util.Scanner;

public class ReadStandard {

    public static void main(String[] args) {

        try(
                Scanner sc = new Scanner(System.in);
                //输出自动创建
                PrintStream ps = new PrintStream(new FileOutputStream("out.txt"));
                )
        {
            sc.useDelimiter("\n");
            while(sc.hasNext()){
                ps.println(sc.next());
            }
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
