package dao;

import entity.*;

import java.util.*;


public class BookSaleRecordDaoImpl extends CommonDaoImpl implements BookSaleRecordDao{

    @Override
    public Collection<BookSaleRecord> findBySaleRecordId(String saleRecordId)
            throws Exception
    {
        String sql = "select * from t_book_sale_record r where r.t_sale_record_id_fk = '" +
                saleRecordId + "'";
        return getDatas(sql,new ArrayList(),BookSaleRecord.class);
    }

    @Override
    public BookSaleRecord findById(String id) throws Exception {
        String sql = "select * from t_book_sale_record r where r.id = '" + id + "'";
        return getSigle(sql);
    }

    @Override
    public BookSaleRecord addOrUpdate(BookSaleRecord bsr) throws Exception {

        Book b = bsr.getBook();
        bsr.setBook_id_fk(new BookDaoImpl().addOrUpdate(b).getId());

        SaleRecord sr = bsr.getSaleRecord();
        //得到SR表的id
        String srId = new SaleRecordDaoImpl().addOrUpdate(sr).getId();
        bsr.setT_sale_record_id_fk(srId);

        boolean flag = false;
        String sql = null;
        String id = null;
        //根据SR表id查询
        Collection<BookSaleRecord> bookSaleRecords = findBySaleRecordId(srId);
        for (BookSaleRecord bookSaleRecord : bookSaleRecords){
            //根据书W判断相等
            if (bookSaleRecord.getBook_id_fk().equals(bsr.getBook_id_fk())){
                flag = true;
                id = bookSaleRecord.getId();
            }
        }
        if(flag){
            sql = "update t_book_sale_record r set r.book_id_fk = '" + bsr.getBook_id_fk() +
                    "', r.t_sale_record_id_fk = '" + bsr.getT_sale_record_id_fk() + "'," +
                    " r.trade_sum = '" + bsr.getTrade_sum() + "'where r.id = '" + id + "'";
            getJDBCExector().executeUpdate(sql);
        }
        else {
            sql = "insert into t_book_sale_record values(null,'" + bsr.getBook_id_fk() +
                    "','" + bsr.getT_sale_record_id_fk() + "','" + bsr.getTrade_sum() + "')";
            id = String.valueOf(getJDBCExector().executeUpdate(sql));
        }
        return findById(id);
    }


    private BookSaleRecord getSigle(String sql) throws Exception{
        BookSaleRecord result = null;
        for (Object b : getDatas(sql,new ArrayList(),BookSaleRecord.class)){
            result = (BookSaleRecord)b;
        }
        return result;
    }
}
