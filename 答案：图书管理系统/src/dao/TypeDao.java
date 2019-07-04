package dao;

import entity.Type;

import java.util.Collection;

public interface TypeDao {
    //根据id查找
    Type findById(String id) throws Exception;
    //获取所有种类
    Collection<Type> findAll() throws Exception;
    //模糊查询
    Collection<Type> findByName(String name) throws Exception;
    //新增种类（存在就修改）
    Type addOrUpdate(Type type) throws Exception;
}
