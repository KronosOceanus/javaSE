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

    public CachedRowSet query(String sql,int pageSize,int page) throws Exception{
        Class.forName(driver);
        try(
                //连接，声明，结果集
                Connection conn = DriverManager.getConnection(url,user,pass);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ){
            RowSetFactory rsf = RowSetProvider.newFactory();
            CachedRowSet cachedRs = rsf.createCachedRowSet();
            //设置每页记录行数
            cachedRs.setPageSize(pageSize);
            //包装的同时设置从第几条记录开始
            cachedRs.populate(rs,(page - 1) * pageSize + 1);
            return cachedRs;
        }
    }
    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.initParam("mysql.ini");
        CachedRowSet cRs = m.query("select * from jdbc_test",3,2);
        while(cRs.next()){
            System.out.println(cRs.getString(1) + "\t"
                    + cRs.getString(2));
        }
    }
}
