import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {

    public static void main(String[] args) {

        String src = "从明天起，做一个幸福的人\n"+
                "喂马，劈柴，周游世界\n"+
                "从明天起,关心粮食和蔬菜\n"+
                "我有一所房子，面朝大海，春暖花开\n"+
                "从明天起,和每一个亲人通信\n"+
                "告诉他们我的幸福\n";
        char[] buffer =new char[32];	 
        int hasRead = 0;
        try(
				//输入输出流没有关联
                StringReader sr = new StringReader(src);
                StringWriter sw = new StringWriter();
                )
        {
            while((hasRead = sr.read(buffer)) > 0){
                sw.write(buffer,0,hasRead);
                System.out.println(new String(buffer,0,hasRead));
            }

            System.out.println("====下面是sw字符串节点里的内容====");
            System.out.println(sw.toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
