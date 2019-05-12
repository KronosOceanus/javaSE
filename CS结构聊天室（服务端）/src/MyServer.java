import java.net.Socket;
import java.util.*;

//服务器
public class MyServer {
    //保存所有Socket
    public static List<Socket> socketList = Collections.synchronizedList(new ArrayList<>());
}
