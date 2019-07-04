package main;

import java.sql.*;

//执行jdbc操作
public class JDBCExector{

    private static String driver = PropsUtil.j_driver;
    private static String url = PropsUtil.j_url;
    private static String user = PropsUtil.j_user;
    private static String pass = PropsUtil.j_pass;
    private Connection conn;
    //单例类维护conn（创建conn成本巨大）
    private static JDBCExector jdbcExector;
    private Statement stmt;

    private JDBCExector() throws Exception {
        Class.forName(driver);
        conn = DriverManager.getConnection(url,user,pass);
        stmt = conn.createStatement();
    }
    public static JDBCExector getInstance() throws Exception {
        if (jdbcExector == null){
            return new JDBCExector();
        }
        return jdbcExector;
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        ResultSet result = stmt.executeQuery(sql);
        return result;
    }
    public int executeUpdate(String sql) throws SQLException {
        int result = -1;
        //必须指定该属性才能得到主键
        stmt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
        //得到主键
        ResultSet primarys = stmt.getGeneratedKeys();
        while(primarys.next()){
            result = primarys.getInt(1);
        }
        return result;
    }

}
