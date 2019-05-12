package dao;

import database.*;

import java.util.Collection;

public interface SaleRecordDao {

    //得到所有销售记录日期
    Collection<SaleRecord> findAll() throws Exception;
    //日期（实质还是字符串）查询
    Collection<SaleRecord> findByDate(String date) throws Exception;
    //id查询
    SaleRecord findById(String id) throws Exception;
    //添加交易日期
    SaleRecord addOrUpdate(SaleRecord saleRecord) throws Exception;
}
