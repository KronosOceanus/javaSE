package utils;

import data_source_impl.MyDataSourceMax;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 控制事务与多线程 Connection
 */
public class MyDataSourceUtils {

    //数据库连接池
    private static MyDataSourceMax dataSource = new MyDataSourceMax();
    //储存当前线程 Connection（相当于为该线程绑定一个 Connection）
    private static ThreadLocal<Connection> tl = new ThreadLocal<>();

    //事务
    public static void startTransaction() throws SQLException {
        getCurrentConnetion().setAutoCommit(false);
    }
    public static void rollback() throws SQLException{
        getCurrentConnetion().rollback();
    }
    public static void commit() throws SQLException{
        getCurrentConnetion().commit();
    }
    public static void release() throws SQLException{
        //将 Connection 从 ThreadLocal 中移除
        tl.remove();
        //注意，此时使用的是 MyConnection
        getCurrentConnetion().close();
    }


    //获取当前线程连接
    public static Connection getCurrentConnetion() throws SQLException {
        //先从 ThreadLocal 中获取
        Connection conn = tl.get();
        //如果 ThreadLocal 中没有，再从数据库连接池获取，并放入当前线程
        if (conn == null){
            conn = dataSource.getConnection();
            tl.set(conn);
        }
        return conn;
    }
}
