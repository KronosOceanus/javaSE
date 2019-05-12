package dao;

import database.Book;
import database.BookSaleRecord;

import java.util.Collection;

public interface BookSaleRecordDao {

    //根据外键id = 销售记录id，在从表中查询记录（一个销售日期对应多个销售记录）
    Collection<BookSaleRecord> findBySaleRecordId(String saleRecordId)
            throws Exception;
    //id查询
    BookSaleRecord findById(String id) throws Exception;
    //添加（修改）单个书的交易记录，外键必须存在！！！
    BookSaleRecord addOrUpdate(BookSaleRecord bookSaleRecord) throws Exception;
}
