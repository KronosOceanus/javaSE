import java.io.*;

public class Main {

    //寻找字符串new PushbackReader并打印该字符串之前的内容
    //读取，查找，找到，推回，重新读取，打印
    public static void main(String[] args) throws IOException{

        try(
                //新建输入文件包装成PushbackReader
                PushbackReader pr = new PushbackReader(new FileReader("PushbackTest.java"),64);
                )
        {
            char[] buf = new char[32];
            //保存上次读取的字符串内容
            String lastContent = "";
            int hasRead = 0;
            while((hasRead = pr.read(buf)) > 0){
                String content = new String(buf,0,hasRead);
                int targetIndex = 0;
                //字符串拼接起来,indexOf输出索引，不存在的话返回-1
                if((targetIndex = (lastContent + content).indexOf("new PushbackReader")) > 0){
                    //变为字符数组推回缓冲区（不读取）
                    pr.unread((lastContent + content).toCharArray());
                    //如果长度超出32，就再定义一个字符数组
                    if(targetIndex > 32){
                        buf = new char[targetIndex];
                    }
                    //再次读取并打印
                    pr.read(buf,0,targetIndex);
                    System.out.println(new String(buf,0,targetIndex));
                    System.exit(0);
                }
                else{
                    //打印上次读取的内容
                    System.out.println(lastContent);
                    //将本次内容设置为上次内容
                    lastContent = content;
                }
            }
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
