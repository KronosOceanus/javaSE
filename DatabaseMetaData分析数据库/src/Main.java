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

    public void info() throws Exception{
        Class.forName(driver);
        try(
                Connection conn = DriverManager.getConnection(url,user,pass);
                ){
            DatabaseMetaData dbmd = conn.getMetaData();

            //获取数据库支持的表类型
            ResultSet rs = dbmd.getTableTypes();
            System.out.println("====mysql支持的表类型信息====");
            printResultSet(rs);

            //获取当前数据库全部数据表
            //参数（目录，模式匹配表达式，表名匹配表达式，……）
            rs = dbmd.getTables(null,null,"%"
                    ,new String[]{"TABLE"});
            System.out.println("====当前数据库全部数据表信息====");
            printResultSet(rs);

            //获取jdbc_test的主键
            //参数（目录，模式，表名）
            rs = dbmd.getPrimaryKeys(null,null,"jdbc_test");
            System.out.println("====jdbc_test表的主键信息====");
            printResultSet(rs);

            //获取当前数据库的全部储存过程
            rs = dbmd.getProcedures(null,null,"%");
            System.out.println("====当前数据库的储存过程信息====");
            printResultSet(rs);

            //获取jdbc_test的全部数据列
            //参数（目录，模式匹配表达式，表名匹配表达式，列名匹配表达式）
            rs = dbmd.getColumns(null,null
                    ,"jdbc_test","%");
            System.out.println("====jdbc_test表的全部数据列====");
            printResultSet(rs);
        }
    }

    public void printResultSet(ResultSet rs) throws Exception{
        ResultSetMetaData rsmd = rs.getMetaData();
        //打印列名
        for (int i=0;i<rsmd.getColumnCount();i++){
            System.out.print(rsmd.getColumnName(i + 1) + "\t");
        }
        System.out.print("\n");
        //打印数据
        //下一行
        while(rs.next()){
            for (int i=0;i<rsmd.getColumnCount();i++){
                System.out.print(rs.getString(i + 1) + "\t");
            }
            System.out.print("\n");
        }
        rs.close();
    }
    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.initParam("mysql.ini");
        m.info();
    }
}
