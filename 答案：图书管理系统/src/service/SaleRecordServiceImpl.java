package service;

import dao.*;
import entity.*;

import java.util.*;

public class SaleRecordServiceImpl implements SaleRecordService {

    //调用Book业务层接口，用于得到有关联的书
    private BookService bookService = new BookServiceImpl(
            new BookDaoImpl(),new TypeDaoImpl(),
            new PublisherDaoImpl());

    private BookDao bDao;
    private SaleRecordDao sDao;
    private BookSaleRecordDao bsDao;
    public SaleRecordServiceImpl(SaleRecordDao sDao,BookSaleRecordDao bsDao){
        this.bDao = bDao;
        this.sDao = sDao;
        this.bsDao = bsDao;
    }

    @Override
    public Collection<SaleRecord> getAll() throws Exception {
        Collection<SaleRecord> saleRecords = sDao.findAll();
        for (SaleRecord s : saleRecords){
            //成员变量赋值
            processDatas(s);
        }
        return saleRecords;
    }

    @Override
    public Collection<SaleRecord> findByDate(String date) throws Exception {
        Collection<SaleRecord> saleRecords = sDao.findByDate(date);
        for (SaleRecord s : saleRecords){
            //成员变量赋值
            processDatas(s);
        }
        return saleRecords;
    }

    @Override
    public Collection<BookSaleRecord> findBySaleRecordId(String saleRecordId)
            throws Exception {
        return bsDao.findBySaleRecordId(saleRecordId);
    }

    @Override
    public BookSaleRecord addOrUpdate(BookSaleRecord b) throws Exception {
        return bsDao.addOrUpdate(b);
    }

    //赋值SaleRecord成员变量
    private void processDatas(SaleRecord s)throws Exception
    {
        //得到SaleRecord对应的BookSaleRecord集合
        ArrayList<BookSaleRecord> bookSaleRecords = (ArrayList<BookSaleRecord>)
                bsDao.findBySaleRecordId(s.getId());

        if (bookSaleRecords.size() != 0){
            //赋值
            s.setBookSaleRecords(bookSaleRecords);
            //其他三个成员变量
            int amount = 0;
            double totalPrice = 0;
            //数组和计数变量
            String[] bookNames = new String[bookSaleRecords.size()];
            int i = 0;
            //计算
            for (BookSaleRecord b : bookSaleRecords){

                Book book = bookService.findById(b.getBook_id_fk());
                double price = Double.parseDouble(book.getBook_price());
                int trade_sum = Integer.parseInt(b.getTrade_sum());
                amount += trade_sum;
                totalPrice += trade_sum * price;
                bookNames[i] = book.getBook_name();

                i++;
            }
            //赋值
            s.setAmount(amount);
            s.setTotalPrice(totalPrice);
            s.setBookNames(bookNames);
        }
    }
}
