import java.util.Scanner;
import java.util.regex.*;

public class Main {

    public static void main(String[] args)
    {
        //获取键盘输入
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine())
        {
            //注意圆括号
            Matcher m = Pattern.compile("((13)|(15))\\d{9}").matcher(sc.nextLine());
            while(m.find())
            {
                System.out.println(m.group());
            }
        }
    }
}
