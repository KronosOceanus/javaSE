import java.io.*;
import java.net.*;

/**
 * 创建集合保存映射关系
 * 监听来自客户端的请求
 */

public class Server {
    private static final int SERVER_POST = 30000;
    public static CrazyitMap<String, PrintStream> clients = new CrazyitMap<>();

    public void init() {
        try(
                ServerSocket ss = new ServerSocket(SERVER_POST);
                ){
            while(true){
                Socket socket = ss.accept();
                new ServerThread(socket).start();
            }
        }catch (IOException e){
            System.out.println("服务器启动失败，是否端口：" + SERVER_POST + " 已被占用？");
        }
    }
}
