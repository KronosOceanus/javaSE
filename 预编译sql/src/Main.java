import java.io.FileInputStream;
import java.util.Properties;
import java.sql.*;

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

    public void insertUseStatement() throws Exception{
        Class.forName(driver);
        try(
                Connection conn = DriverManager.getConnection(url,user,pass);
                //主键列无法插入内容
                PreparedStatement pstmt = conn.prepareStatement("insert into jdbc_test(test_name) values(?)" );
        ){
            for(int i=0;i<10;i++){
                pstmt.setString(1,"姓名" + i);
                pstmt.executeUpdate();
            }
        }
    }
    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.initParam("mysql.ini");
        m.insertUseStatement();
    }
}
