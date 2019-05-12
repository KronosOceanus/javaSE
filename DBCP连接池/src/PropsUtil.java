import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropsUtil {

    static String driver;
    static String url;
    static String user;
    static String pass;
    //静态初始化块
    private static Properties props = new Properties();
    private static String fileName = "mysql.ini";
    private static InputStream is;

    static{
        try {
            is = new FileInputStream(fileName);
            props.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver = props.getProperty("driver");
        url = props.getProperty("url");
        user = props.getProperty("user");
        pass = props.getProperty("pass");
    }
}
