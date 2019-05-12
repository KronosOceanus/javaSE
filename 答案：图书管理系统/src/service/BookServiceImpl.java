package service;

import dao.BookDao;
import dao.PublisherDao;
import dao.TypeDao;
import database.Book;
import database.Type;

import java.util.Collection;

public class BookServiceImpl implements BookService {

    //外键约束需要接口，为外键设置引用
    private BookDao bDao;
    private TypeDao tDao;
    private PublisherDao pDao;
    public BookServiceImpl(BookDao bDao,TypeDao tDao,PublisherDao pDao){
        this.bDao = bDao;
        this.tDao = tDao;
        this.pDao = pDao;
    }

    @Override
    public Collection<Book> getAll() throws Exception {
        Collection<Book> result = bDao.findAll();
        return setAssociate(result);
    }

    @Override
    public Collection<Book> findByName(String name) throws Exception {
        Collection<Book> result = bDao.findByName(name);
        return setAssociate(result);
    }

    @Override
    public Book findById(String id) throws Exception {
        Book result = bDao.findById(id);
        setAssociate(result);
        return result;
    }

    @Override
    public Collection<Type> getAllType() throws Exception {
        return tDao.findAll();
    }

    @Override
    public Collection<Book> findByType(String type) throws Exception {
        Collection<Book> result = bDao.findByType(type);
        return setAssociate(result);
    }

    @Override
    public Book addOrUpdate(Book book) throws Exception {
        Book result = bDao.addOrUpdate(book);
        return setAssociate(result);
    }

    //设置外键引用
    private Collection<Book> setAssociate(Collection<Book> result)
            throws Exception {
        for (Book book : result){
            book.setType(tDao.findById(book.getType_id_fk()));
            book.setPublisher(pDao.findById(book.getPub_id_fk()));
        }
        return result;
    }
    private Book setAssociate(Book book) throws Exception{
        book.setType(tDao.findById(book.getType_id_fk()));
        book.setPublisher(pDao.findById(book.getPub_id_fk()));
        return book;
    }
}
