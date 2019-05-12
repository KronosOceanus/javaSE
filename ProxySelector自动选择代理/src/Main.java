import java.io.*;
import java.net.*;
import java.util.*;

/**
 * ProxySelector是抽象类
 * 重写select，connectFailed方法后
 * 通过静态方法注册ProxySelector
 * 每次打开URL连接使用select中返回的Proxy
 */
public class Main {

    //代理服务器的IP和端口
    final String PROXY_ADDR = "139.82.12.188";
    final int PROXY_PORT = 3124;
    String urlStr = "http://www.crazyit.org";

    //URL格式错误异常
    public void init()throws IOException,MalformedURLException{

        ProxySelector.setDefault(new ProxySelector() {
            //系统连接失败回调这两个方法
            @Override
            public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
                System.out.println("无法连接到指定服务器!");
            }
            //连接失败后，或未指定代理，则默认使用该方法返回的Proxy
            @Override
            public List<Proxy> select(URI uri) {
                List<Proxy> result = new ArrayList<>();
                result.add(new Proxy(Proxy.Type.HTTP,
                        new InetSocketAddress(PROXY_ADDR,PROXY_PORT)));
                return result;
            }
        });

        URL url = new URL(urlStr);
        //没有指定Proxy，回调PRoxySelector中的两个方法获取默认Proxy
        URLConnection conn = url.openConnection();
        try(
                Scanner scan = new Scanner(conn.getInputStream());
                PrintStream ps = new PrintStream("index.htm");
        ){
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                System.out.println(line);
                ps.println(line);
            }
        }
    }
    public static void main(String[] args) throws Exception{
        new Main().init();
    }
}
