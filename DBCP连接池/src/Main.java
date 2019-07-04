import data_source_impl.MyDataSource;
import data_source_impl.MyDataSourceMax;
import dbcp.DBCPUtils;
import dbcp.JDBCUtils;

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

    //MyDataSourceMax测试
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

    //DataSource与DBUtils结合测试
    private void testDS() throws SQLException {
        String sql = "update t_book_type set type_name = '经济学' where id = 22";
        JDBCUtils.qr.update(sql);
    }

    //DBCP测试
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


    public static void main(String[] args) throws Exception {
        Main m = new Main();
        m.testDS();
        m.testMyDataSource();
        m.testDBCP();
    }
}
