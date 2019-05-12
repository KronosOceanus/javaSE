import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class Main {

    private String driver;
    private String url;
    private String user;
    private String pass;
    public void initParam(String paramFile) throws Exception{

        //加载属性文件
        Properties props = new Properties();
        props.load(new FileInputStream(paramFile));
        driver = props.getProperty("driver");
        url = props.getProperty("url");
        user = props.getProperty("user");
        pass = props.getProperty("pass");

    }

    public void createTable(String sql) throws Exception{
        Class.forName(driver);
        try(
                Connection conn = DriverManager.getConnection(url,user,pass);
                Statement stmt = conn.createStatement();
                )
        {
            stmt.executeUpdate(sql);
        }
    }

    public int insertData(String sql) throws Exception{
        Class.forName(driver);
        try(
                Connection conn = DriverManager.getConnection(url,user,pass);
                Statement stmt = conn.createStatement();
        )
        {
            return stmt.executeUpdate(sql);
        }
    }

    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.initParam("mysql.ini");
        m.createTable("create table jdbc_test"
            + "(jdbc_id int auto_increment primary key,"
            + "jdbc_name varchar(255),"
            + "jdbc_desc text);");
        System.out.println("建表成功！");

        int result = m.insertData("insert into jdbc_test(jdbc_name,jdbc_desc)"
            + "values('cs','what?!')");
        System.out.println("插入成功！" + "一共有: " + result + " 条记录受到影响！");
    }
}
