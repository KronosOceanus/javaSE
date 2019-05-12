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

    public void batchUpdate(String... sqls) throws Exception{
        Class.forName(driver);
        try(
                Connection conn = DriverManager.getConnection(url,user,pass);
                Statement stmt = conn.createStatement();
                ){
            //保存当前提交模式
            boolean autoCommit = conn.getAutoCommit();
            //关闭自动提交
            conn.setAutoCommit(false);
            for (String sql:sqls){
                //不能提交查询语句
                stmt.addBatch(sql);
            }
            //批量执行
            stmt.executeBatch();
            //提交修改
            conn.commit();
            //恢复提交模式
            conn.setAutoCommit(autoCommit);
        }
    }

    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.initParam("mysql.ini");
        //不能提交查询语句！
        String[] sqls = {
                "insert into jdbc_test(test_name) values('a')",
                "insert into jdbc_test(test_name) values('b')",
                "insert into jdbc_test(test_name) values('c')",
                "insert into jdbc_test(test_name) values('d')"
        };
        m.batchUpdate(sqls);
    }
}
