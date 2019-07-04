package service;

import entity.Book;
import entity.Type;

import java.util.Collection;

public interface BookService {

    //获取所有书
    Collection<Book> getAll() throws Exception;
    //模糊查询
    Collection<Book> findByName(String name) throws Exception;
    //id查询
    Book findById(String id) throws Exception;
    //获取所有种类
    Collection<Type> getAllType() throws Exception;
    //根据种类查询书
    Collection<Book> findByType(String type) throws Exception;
    //新增书（修改）
    Book addOrUpdate(Book book) throws Exception;
}
