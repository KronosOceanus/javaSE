import java.io.*;
import java.sql.*;
import java.util.Properties;

public class Main {

    private String driver;
    private String url;
    private String user;
    private String pass;
    public void initParam(String paramFile) throws Exception{

        Properties props = new Properties();
        props.load(new FileInputStream(paramFile));
        driver = props.getProperty("driver");
        url = props.getProperty("url");
        user = props.getProperty("user");
        pass = props.getProperty("pass");
    }

    public void insetInTransaction(String[] sqls) throws Exception{
        Class.forName(driver);
        try(
                Connection conn = DriverManager.getConnection(url,user,pass);
                ){
            //关闭自动提交，开启事务支持
            conn.setAutoCommit(false);
            try(
                    Statement stmt = conn.createStatement();
                    ){
                //多条DML语句
                for(String sql:sqls){
                    stmt.executeUpdate(sql);
                }
            }
            //提交事务
            conn.commit();
        }
    }

    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.initParam("mysql.ini");
        String[] sqls = {"insert into jdbc_test(test_name) values('a')",
                "insert into jdbc_test(test_name) values('b')",
                "insert into jdbc_test(test_name) values('c')",
                "insert into jdbc_test(test_name) values('d')",
                "rollback"
        };
        m.insetInTransaction(sqls);
    }
}
