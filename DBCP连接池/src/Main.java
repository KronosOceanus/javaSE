import data_source_impl.MyDataSource;
import data_source_impl.MyDataSourceMax;
import dbcp.DBCPUtils;
import dbcp.JDBCUtils;
import utils.MyDataSourceUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DBCP实现了数据源DataSource接口
 */
public class Main {

    private Connection conn = null;
    private Statement stmt = null;

/**
    //MyDataSource测试
    private void testMyDataSource(){
        Connection conn = null;
        Statement stmt = null;
        MyDataSource dataSource = new MyDataSource();
        try{
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "update t_book_type set type_name = '政治学' where id = 22";
        try{
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dataSource.backConnection(conn);
        }
    }
*/

    // MyDataSourceMax 测试
    private void testMyDataSource(){
        MyDataSourceMax dataSource = new MyDataSourceMax();
        String sql = "update t_book_type set type_name = '政治学' where id = 22";

        try{
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dataSource.backConnection(conn);
        }
    }
    // DataSource 与 DBUtils 结合测试
    private void testDS() throws SQLException {
        String sql = "update t_book_type set type_name = '经济学' where id = 22";
        JDBCUtils.qr.update(sql);
    }
    // DBCP 测试
    private void testDBCP(){
        String sql = "update t_book_type set type_name = '法学' where id = 22";
        try{
            conn = DBCPUtils.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // ThreadLocal 连接池测试
    private void testTL() throws SQLException {
        try {
            stmt = MyDataSourceUtils.getCurrentConnetion().createStatement();
            MyDataSourceUtils.startTransaction();
            stmt.executeUpdate("update t_book_type set type_name = '政治' where id = 22");
            int i=1/0;
            stmt.executeUpdate("update t_book_type set type_name = '政治学学学' where id = 22");
            MyDataSourceUtils.commit();
        } catch (Exception e) {
            MyDataSourceUtils.rollback();
            e.printStackTrace();
        }finally {
            MyDataSourceUtils.commit();
        }
    }

    public static void main(String[] args) throws Exception {
        Main m = new Main();
        m.testTL();
    }
}
