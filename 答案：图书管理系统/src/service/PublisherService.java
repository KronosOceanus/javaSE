package service;

import database.Publisher;
import database.ValueObject;

import java.util.Collection;

public interface PublisherService {

    //得到全部出版社
    Collection<Publisher> getAll() throws Exception;
    //模糊查询
    Collection<Publisher> findByName(String name) throws Exception;
    //查看出版社
    Publisher findById(String id) throws Exception;
    //新增出版社（修改）
    Publisher addOrUpdate(Publisher publisher) throws Exception;
}
