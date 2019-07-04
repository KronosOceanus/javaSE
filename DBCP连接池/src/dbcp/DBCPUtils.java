package dbcp;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBCPUtils {

    private static DataSource dataSource;

    //创建数据源
    static{
        try{
            //注意配置文件的key（必须是driver、url、username、password）
            InputStream is = DBCPUtils.class.getClassLoader().getResourceAsStream("db.properties");
            Properties props = new Properties();
            props.load(is);
            dataSource = BasicDataSourceFactory.createDataSource(props);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取数据源
    public static DataSource getDataSource(){
        return dataSource;
    }

    //得到连接
    public static Connection getConnection(){
        try{
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
