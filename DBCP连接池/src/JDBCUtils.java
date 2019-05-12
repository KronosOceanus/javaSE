import org.apache.commons.dbcp2.BasicDataSource;
/**
 * DBCP实现工具类
 */
public class JDBCUtils {

    private static String driver = PropsUtil.driver;
    private static String url = PropsUtil.url;
    private static String user = PropsUtil.user;
    private static String pass = PropsUtil.pass;

    //连接池基类
    public static BasicDataSource bds;

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
    }
}
