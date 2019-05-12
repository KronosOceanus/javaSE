package dao;

import database.*;
import main.ImageUtil;

import java.util.ArrayList;
import java.util.Collection;

public class BookDaoImpl extends CommonDaoImpl implements BookDao {

    @Override
    public Collection<Book> findAll() throws Exception {
        String sql = "select * from t_book bk order by bk.id desc";
        return getDatas(sql,new ArrayList(), Book.class);
    }

    @Override
    public Collection<Book> findByName(String name) throws Exception {
        String sql = "select * from t_book b where book_name like " +
                "'%" + name + "%' order by b.id desc";
        return getDatas(sql,new ArrayList(),Book.class);
    }

    @Override
    public Book findById(String id) throws Exception {
        String sql = "select * from t_book b where b.id like '" + id + "'";
        return getSingle(sql);
    }

    @Override
    public Collection<Book> findByType(String type) throws Exception {
        String sql = "select * from t_book_type bt where bt.type_name like '" + type + "'";
        String id = null;
        for(Object t : getDatas(sql,new ArrayList(),Type.class)){
            id = ((Type)t).getId();
        };
        sql = "select * from t_book b where b.type_id_fk like '" + id + "'" +
                "order by b.id desc";
        return getDatas(sql,new ArrayList(),Book.class);
    }

    @Override
    public Book addOrUpdate(Book b) throws Exception {
        //根据引用类型变量设置外键
        Type t = b.getType();
        b.setType_id_fk(new TypeDaoImpl().addOrUpdate(t).getId());

        Publisher p = b.getPublisher();
        b.setPub_id_fk(new PublisherDaoImpl().addOrUpdate(p).getId());

        //得到图片到当前路径
        if (b.getImage_url() != null){
            ImageUtil.addImage(b.getImage_url());
        }

        boolean flag = false;
        String sql = null;
        String id = null;
        //库存
        int repertory_size = 0;
        Collection<Book> books = findAll();
        for (Book book : books){
            if (book.getBook_name().equals(b.getBook_name())){
                flag = true;
                id = book.getId();
                repertory_size = Integer.parseInt(book.getRepertory_size());
            }
        }
        if (flag){
            sql = "update t_book b set b.book_name = '" + b.getBook_name() + "',b.book_intro" +
                    " = '" + b.getBook_intro() + "',b.book_price = '" + b.getBook_price() + "'," +
                    "b.type_id_fk = '" + b.getType_id_fk() + "',b.pub_id_fk = '" + b.getPub_id_fk() +
                    "',b.image_url = '" + b.getImage_url() + "',b.author = '" + b.getAuthor() +
                    "',b.repertory_size = '" + String.valueOf(repertory_size + 1) + "'" +
                    "where b.id like '" + id + "'";
            getJDBCExector().executeUpdate(sql);
        }else {
            sql = "insert into t_book values(null,'" + b.getBook_name() +
                    "','" + b.getBook_intro() + "','" + b.getBook_price() + "','" +
                    b.getType_id_fk() + "','" + b.getPub_id_fk() + "','" +
                    b.getImage_url() + "','" + b.getAuthor() + "','0')";
            id = String.valueOf(getJDBCExector().executeUpdate(sql));
        }
        return findById(id);
    }


    //根据sql得到单体
    private Book getSingle(String sql) throws Exception{
        Book book = null;
        for (Object b : getDatas(sql,new ArrayList(),Book.class)){
            book = (Book)b;
        }
        return book;
    }

}
