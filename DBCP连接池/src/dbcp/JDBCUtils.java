package dbcp;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import utils.PropsUtils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * DBCP实现工具类
 */
public class JDBCUtils {

    //也可直接使用PropsUtils.xxx
    private static String driver = PropsUtils.driver;
    private static String url = PropsUtils.url;
    private static String user = PropsUtils.user;
    private static String pass = PropsUtils.pass;

    //连接池基类
    private static BasicDataSource bds;
    //直接创建连接
    public static Connection conn;
    //与DBUtils结合使用
    public static QueryRunner qr;

    static{
        bds = new BasicDataSource();
        bds.setDriverClassName(driver);
        bds.setUrl(url);
        bds.setUsername(user);
        bds.setPassword(pass);

        bds.setInitialSize(10); //初始连接数量
        bds.setMaxTotal(8); //最大连接数量
        bds.setMaxIdle(5); //最大空闲连接
        bds.setMinIdle(1); //最小空闲连接

        try {
            conn = bds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //传入数据源
        qr = new QueryRunner(bds);
    }
}
