import java.sql.*;

public class Main {

    public static void main(String[] args) throws Exception {

        //反射加载数据库驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        try(
                //连接（mysql-8.0以上需要url需要指定时区）
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://127.0.0.1:3306/classicmodels" +
                                "?useSSL=false&serverTimezone=UTC"
                    ,"root","java521....");
                Statement stmt = conn.createStatement();
                //执行sql语句
                ResultSet rs = stmt.executeQuery("select *  from customers");
                )
        {
            while (rs.next()){
                //通过列索引得到内容
                System.out.println(rs.getInt(1) + "\t"
                    + rs.getString(2));
            }
        }
    }
}
