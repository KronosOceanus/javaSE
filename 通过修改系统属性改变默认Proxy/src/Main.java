import java.io.FileInputStream;
import java.net.*;
import java.util.*;

public class Main {

    static String urlStr = "https://www.2345.com/?39291";

    public static void main(String[] args) throws Exception{

        //读取属性文件（Properties实际上是一个HashMap）
        Properties myProps = new Properties();
        myProps.load(new FileInputStream("properties.ini"));
        //获取系统信息
        Properties props = System.getProperties();
        //迭代设置系统属性，获取String类型的key
        for(String key : myProps.stringPropertyNames()){
            //设置访问4种协议的Proxy的IP，端口
            //无需使用Proxy的主机（https，socks不用设置该项）
            props.setProperty(key,myProps.getProperty(key));
            //测试ini文件是否被读取
            System.out.println(key + " = " + myProps.getProperty(key));
        }
        //获取系统默认ProxySelector
        ProxySelector selector = ProxySelector.getDefault();
        System.out.println("系统默认ProxySelector：" + selector);
        System.out.println("系统为该url选择的Proxy为：" +
                ProxySelector.getDefault().select(new URI("ftp://www.2345.com/?39291")));
        URL url = new URL(urlStr);
        URLConnection conn = url.openConnection();
        conn.setConnectTimeout(3000);
        try(
                Scanner scan = new Scanner(conn.getInputStream(),"UTF-8");
                ){
            while(scan.hasNextLine()){
                System.out.println(scan.nextLine());
            }
        }
    }
}
