package dao;

import database.Book;

import java.util.Collection;

public interface BookDao {
    //获取所有书
    Collection<Book> findAll() throws Exception;
    //模糊查询
    Collection<Book> findByName(String name) throws Exception;
    //id查询
    Book findById(String id) throws Exception;
    //根据种类查找书（得到种类id，根据id查询）
    Collection<Book> findByType(String type) throws Exception;
    //新增书（根据外键的名称判断是否存在，存在则得到id，否则新建外键类型）
    Book addOrUpdate(Book book) throws Exception;
}
