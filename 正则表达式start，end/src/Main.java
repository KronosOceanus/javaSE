import java.util.regex.*;

public class Main {
    public static void main(String[] args)
    {
        String str = "Java is my love!";
        //创建匿名正则表达式并与str匹配
        Matcher m =Pattern.compile("\\w+").matcher(str);
        while(m.find())
        {
            System.out.println(m.group()+":开始位置"+m.start()+",结束位置"+m.end());
        }
    }
}
