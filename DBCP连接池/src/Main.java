import org.apache.commons.dbutils.QueryRunner;

/**
 * DBCP实现了数据源DataSource接口
 */
public class Main {

    //以后不需要创建conn对象
    private static QueryRunner qr = new QueryRunner(JDBCUtils.bds);

    public static void main(String[] args) throws Exception {
        Main m = new Main();
    }
}
