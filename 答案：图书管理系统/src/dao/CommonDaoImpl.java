package dao;

import main.*;

import java.sql.ResultSet;
import java.util.Collection;

//数据访问父类
public class CommonDaoImpl {

    public JDBCExector getJDBCExector() throws Exception {
        return JDBCExector.getInstance();
    }

    //根据sql得到结果集，再返回集合
    public Collection getDatas(String sql, Collection result,
                               Class clazz) throws Exception {
        ResultSet rs = getJDBCExector().executeQuery(sql);
        return DataUtil.getData(result,rs,clazz);
    }
}
