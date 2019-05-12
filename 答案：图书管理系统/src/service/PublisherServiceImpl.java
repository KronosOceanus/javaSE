package service;

import dao.PublisherDao;
import database.*;

import java.util.Collection;

public class PublisherServiceImpl implements PublisherService {

    //接口，调用数据访问层
    private PublisherDao dao;
    public PublisherServiceImpl(PublisherDao dao){
        this.dao = dao;
    }
    //得到所有出版社数据
    @Override
    public Collection<Publisher> getAll() throws Exception {
        return dao.findAll();
    }

    @Override
    public Collection<Publisher> findByName(String name) throws Exception {
        return dao.findByName(name);
    }

    @Override
    public Publisher findById(String id) throws Exception {
        return dao.findById(id);
    }

    @Override
    public Publisher addOrUpdate(Publisher p) throws Exception {
        return dao.addOrUpdate(p);
    }

}
