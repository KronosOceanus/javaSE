package data_source_impl;

import utils.PropsUtils;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * 自定义连接池
 */
public class MyDataSource implements DataSource {

    // 1. 创建一个List储存Connection对象
    private static LinkedList<Connection> pool = new LinkedList<>();
    // 2. 创建5个连接放入容器
    static {
        try {
            //加载驱动
            Class.forName(PropsUtils.driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (int i=0;i<5;i++){
            Connection conn = null;
            try {
                //创建连接
                conn = DriverManager.getConnection(PropsUtils.url,
                        PropsUtils.user, PropsUtils.pass);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //添加到连接池
            pool.add(conn);
        }
    }
    // 3. 获取连接
    @Override
    public Connection getConnection() throws SQLException {
        //新建连接对象
        Connection conn = null;
        //池内没有连接，创建连接
        if (pool.size() == 0) {
            for (int i = 0; i < 5; i++) {
                try {
                    //创建连接
                    conn = DriverManager.getConnection(PropsUtils.url,
                            PropsUtils.user, PropsUtils.pass);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //添加到连接池
                pool.add(conn);
            }
        }
        //使用前先从List中移除连接
        conn = pool.remove(0);
        return conn;
    }
    // 4. 归还连接
    public void backConnection(Connection conn){
        pool.add(conn);
    }
















    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}
