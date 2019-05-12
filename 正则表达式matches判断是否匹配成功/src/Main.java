import java.util.regex.*;

public class Main {

    public static void main(String[] args){
        //定义并初始化字符串数组
        String[] ss ={
                "kongeeku@163.com",
                "kongyeeku@gamil.com",
                "ligang@crazyit.org",
                "wawa@abc.xx"
        };
        //创建正则表达式（赋值给String对象）
        String str = "\\w{3,20}@\\w+\\.(com|org|cn|net|gov)";
        //创建Pattern对象用于匹配操作
        Pattern p = Pattern.compile(str);
        //m用于匹配
        Matcher m = null;
        for(String mail:ss)
        {
            //如果m为null，将str与mail匹配结果赋值给m
            if(m == null)
            {
                m = p.matcher(mail);
            }
            //否则用reset()方法直接调用现有的m，并与mail匹配
            else
            {
                m.reset(mail);
            }
            //m.matches()方法判断是否（全部）匹配成功（返回值为boolean）
            System.out.println(mail+(m.matches()?" is":" isn't")+" a correct email address！");
        }
    }
}
