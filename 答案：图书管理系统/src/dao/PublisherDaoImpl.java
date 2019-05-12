package dao;

import database.*;

import java.util.ArrayList;
import java.util.Collection;

//数据访问层的实现
public class PublisherDaoImpl extends CommonDaoImpl implements PublisherDao {

    @Override
    public Collection<Publisher> findAll() throws Exception {
        String sql = "select * from t_publisher pub order by pub.id desc";
        //得到父类型的数据
        //同时还能控制返回集合的种类
        return getDatas(sql,new ArrayList(),Publisher.class);
    }

    @Override
    public Collection<Publisher> findByName(String name) throws Exception {
        String sql = "select * from t_publisher pub where pub_name like " +
                "'%" + name + "%' order by pub.id desc";
        return getDatas(sql,new ArrayList(),Publisher.class);
    }

    @Override
    public Publisher findById(String id) throws Exception {
        String sql = "select * from t_publisher pub where id = " + id;
        return getSingle(sql);
    }

    @Override
    public Publisher addOrUpdate(Publisher p) throws Exception {
        //false代表数据表中不存在
        boolean flag = false;
        String sql = null;
        String id = null;
        Collection<Publisher> publishers = findAll();
        for (Publisher pub : publishers){
            if (pub.getPub_name().equals(p.getPub_name())){
                flag = true;
                id = pub.getId();
            }
        }
        //存在就修改，否则就插入
        if (flag){
            sql = "update t_publisher pub set pub.pub_name ='" + p.getPub_name() + "'," +
                    "pub.pub_tel ='" + p.getPub_tel() + "',pub.pub_link_man ='"
                    + p.getPub_link_man() + "',pub.pub_intro ='" + p.getPub_intro() + "' " +
                    "where pub.id like '" + id + "'";
            getJDBCExector().executeUpdate(sql);
        }else {
            sql = "insert into t_publisher values(null,'" + p.getPub_name() +
                    "','" + p.getPub_tel() + "','" + p.getPub_link_man()+ "','" +
                    p.getPub_intro() + "')";
            id = String.valueOf(getJDBCExector().executeUpdate(sql));
        }
        return findById(id);
    }


    //根据sql得到单体
    public Publisher getSingle(String sql) throws Exception{
        Publisher publisher = null;
        for (Object p : getDatas(sql,new ArrayList(),Publisher.class)){
            publisher = (Publisher)p;
        }
        return publisher;
    }
}
