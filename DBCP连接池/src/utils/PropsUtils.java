package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PropsUtils {

    public static String driver;
    public static String url;
    public static String user;
    public static String pass;
    //静态初始化块
    private static Properties props = new Properties();
    private static String fileName = "mysql.ini";
    private static InputStream is;

    private static Connection conn;

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
