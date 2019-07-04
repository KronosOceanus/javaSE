package dao;

import entity.Type;

import java.util.ArrayList;
import java.util.Collection;

public class TypeDaoImpl extends CommonDaoImpl implements TypeDao{

    @Override
    public Type findById(String id) throws Exception {
        String sql = "select * from t_book_type bt where bt.id like '" + id + "'" ;
        Type type = null;
        for (Object t : getDatas(sql,new ArrayList(),Type.class)){
            type = (Type) t;
        }
        return type;
    }

    @Override
    public Collection<Type> findAll() throws Exception {
        String sql = "select * from t_book_type bt order by bt.id desc";
        return getDatas(sql,new ArrayList(),Type.class);
    }

    @Override
    public Collection<Type> findByName(String name) throws Exception {
        String sql = "select * from t_book_type bt where bt.type_name like " +
                "'%" + name + "%' order by bt.id desc";
        return getDatas(sql,new ArrayList(),Type.class);
    }

    @Override
    public Type addOrUpdate(Type t) throws Exception {
        boolean flag = false;
        String sql = null;
        String id = null;
        Collection<Type> types = findAll();
        for (Type type : types){
            if (type.getType_name().equals(t.getType_name())){
                flag = true;
                id = type.getId();
            }
        }
        if (flag){
            sql = "update t_book_type bt set bt.type_name = '" + t.getType_name() +
                    "',bt.type_intro = '" + t.getType_intro() + "' " +
                    "where bt.id like '" + id + "'";
            getJDBCExector().executeUpdate(sql);
        }else {
            sql = "insert into t_book_type values(null,'" + t.getType_name() +
                    "','" + t.getType_intro() + "')";
            id = String.valueOf(getJDBCExector().executeUpdate(sql));
        }
        return findById(id);
    }
}
