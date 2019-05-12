package dao;

import database.Publisher;

import java.util.Collection;

public interface PublisherDao {
    //获取所有出版社
    Collection<Publisher> findAll() throws Exception;
    //模糊查询（关键字查询）
    Collection<Publisher> findByName(String name) throws Exception;
    //id查询
    Publisher findById(String id) throws Exception;
    //新增出版社（存在就修改）
    Publisher addOrUpdate(Publisher publisher) throws Exception;
}

