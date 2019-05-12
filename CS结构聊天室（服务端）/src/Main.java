import java.net.*;

//服务器
public class Main {

    /**
     * Socket还可以通过write方法发送http请求
     * 如GET /LDServer/resources/images/1.jpg HTTP/1.1
     * 请求类型，服务器名称，资源路径，http协议版本
     */

    public static void main(String[] args) throws Exception{
        //在本机建立服务端
        ServerSocket ss = new ServerSocket(30000);
        while(true){
            Socket s = ss.accept();
            MyServer.socketList.add(s);
            new Thread(new ServerThread(s)).start();
        }
    }
}
