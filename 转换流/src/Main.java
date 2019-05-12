import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        try(
                //键盘输入System.in是文本内容，包装成字符输入流
                InputStreamReader reader = new InputStreamReader(System.in);
                //再包装成BR使用readLine方法可以一次读取一行
                BufferedReader br = new BufferedReader(reader);
                )
        {
            String line = null;
            while((line = br.readLine()) != null){
                //读取到exit程序退出
                if(line.equals("exit")){
                    System.exit(1);
                }
                System.out.println("输入内容为"+line);
            }
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
