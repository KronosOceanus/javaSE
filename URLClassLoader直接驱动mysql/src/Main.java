import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.util.Properties;

/**
 * 可以不用将mysql驱动添加到环境变量中
 */
public class Main {

    private static Connection conn;
    public static Connection getConn(String url,
        String user,String pass) throws Exception
    {
        if (conn == null){
            //file表示从本地系统文件读取（如果是http代表通过http访问加载）
            URL[] urls = {new URL("file:mysql-connector-java-8.0.15.jar")};
            //使用默认父类加载器创建URLClassLoader对象
            URLClassLoader myClassLoader = new URLClassLoader(urls);
            //反射得到类的实例
            //得到类的弱引用实例的clazz.newInstance()（只适用于无参构造）方法过期，改为如下方法
            Driver driver = (Driver)myClassLoader.loadClass("com.mysql." +
                    "cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            //将账号密码写入属性
            Properties props = new Properties();
            props.setProperty("user",user);
            props.setProperty("password",pass);
            //驱动连接方法，参数（url，包含账号密码的Properties实例）
            driver.connect(url,props);
        }
        return conn;
    }
    public static void main(String[] args) throws Exception{
        Main.getConn("jdbc:mysql://127.0.0.1:3306/mytest" +
                "?useSSL=false&serverTimezone=UTC","root","java521....");
    }
}
