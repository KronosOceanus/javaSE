package service;

import database.*;

import java.util.Collection;
import java.util.Date;

public interface SaleRecordService {

    //得到所有销售记录时间（为其他成员变量赋值）
    Collection<SaleRecord> getAll() throws Exception;
    //通过日期查找销售记录时间（相当于模糊查询）
    Collection<SaleRecord> findByDate(String date) throws Exception;
    //根据总销售记录获得单个记录集合
    Collection<BookSaleRecord> findBySaleRecordId(String saleRecordId)
            throws Exception;
    //添加（修改）单个书的交易记录
    BookSaleRecord addOrUpdate(BookSaleRecord bookSaleRecord) throws Exception;
}
