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

    public void executeSql(String sql) throws Exception {
        Class.forName(driver);
        try (
                Connection conn = DriverManager.getConnection(url, user, pass);
                //结果集控制参数（记录指针可自由移动，结果集可更新）
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery(sql);
        ) {
            //移动记录指针
            rs.last();
            int rowCount = rs.getRow();
            for(int i=rowCount;i>0;i--){
                //绝对移动
                rs.absolute(i);
                System.out.println(rs.getString(1) + "\t" + rs.getString(2));
                //修改并提交，参数（列索引，修改内容）
                rs.updateString(2,"学生名" + i);
                rs.updateRow();
            }
        }
    }

    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.initParam("mysql.ini");
        m.executeSql("select * from jdbc_test;");
    }
}
