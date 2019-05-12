import java.io.*;
import java.net.Socket;

//客户端
public class Main {

    //程序入口
    public static void main(String[] args) throws Exception{
        //连接到本机，端口为30000
        Socket s = new Socket("10.0.117.43",30000);
        //启动线程来获取服务端的数据
        new Thread(new ClientThread(s)).start();
        PrintStream ps = new PrintStream(s.getOutputStream());
        String line = null;
        //获取键盘输入
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while((line = br.readLine()) != null){
            //输出数据到Socket
            ps.println(line);
        }
    }
}