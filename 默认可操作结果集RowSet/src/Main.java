import javax.sql.rowset.*;
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

    public void update(String sql) throws Exception{
        Class.forName(driver);
        RowSetFactory rsf = RowSetProvider.newFactory();
        try(
                JdbcRowSet jdbcRs = rsf.createJdbcRowSet();
        ){
            //未传入Connection对象，所以需要set数据库连接信息
            jdbcRs.setUrl(url);
            jdbcRs.setUsername(user);
            jdbcRs.setPassword(pass);
            jdbcRs.setCommand(sql);

            //执行查询
            jdbcRs.execute();
            jdbcRs.afterLast();
            while(jdbcRs.previous()){
                System.out.println(jdbcRs.getString(1) + "\t"
                    + jdbcRs.getString(2));
                if (jdbcRs.getInt("test_id") == 8){
                    jdbcRs.updateString("test_name","cs");
                    //提交修改
                    jdbcRs.updateRow();
                }
            }
        }
    }
    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.initParam("mysql.ini");
        m.update("select * from jdbc_test");
    }
}
