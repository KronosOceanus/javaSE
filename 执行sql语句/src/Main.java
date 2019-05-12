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

    public void executeSql(String sql) throws Exception{
        Class.forName(driver);
        try(
                Connection conn = DriverManager.getConnection(url,user,pass);
                Statement stmt = conn.createStatement();
        ){
            //执行sql语句，判断有无结果集
            boolean hasResultSet = stmt.execute(sql);
            if(hasResultSet){
                try(
                        ResultSet rs = stmt.getResultSet();
                ){
                    //ResultSetMetaData是分析数据集的元数据接口
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnCount = rsmd.getColumnCount();
                    while(rs.next()){
                        for(int i=0;i<columnCount;i++){
                            System.out.print(rs.getString(i+1) + "\t");
                        }
                        System.out.println("\n");
                    }
                }
            }
            else {
                System.out.println("该sql语句影响的结果有: " + stmt.getUpdateCount() + " 条！");
            }
        }
    }
    public static void main(String[] args) throws Exception {
        Main m = new Main();
        m.initParam("mysql.ini");
        System.out.println("----执行删表语句----");
        m.executeSql("drop table if exists jdbc_test");
        System.out.println("----执行建表语句----");
        m.executeSql("create table jdbc_test"
            + "(test_id int auto_increment primary key,"
            + "test_name varchar(255))");
        System.out.println("----执行插入语句----");
        m.executeSql("insert into jdbc_test(test_name)"
            + "values('cs')");
        System.out.println("----执行查询语句----");
        m.executeSql("select * from jdbc_test");
    }
}
