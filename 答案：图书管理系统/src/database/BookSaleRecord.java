package database;

//所有记录
public class BookSaleRecord extends ValueObject{
    //书W，销售日期W，销售量
    private String book_id_fk;
    private String t_sale_record_id_fk;
    private String trade_sum;

    //外键引用类型
    private Book book;
    private SaleRecord saleRecord;

    public BookSaleRecord(){};
    public BookSaleRecord(Book book,SaleRecord saleRecord,String trade_sum){
        this.book = book;
        this.saleRecord = saleRecord;
        this.trade_sum = trade_sum;
    }

    public String getBook_id_fk() {
        return book_id_fk;
    }

    public void setBook_id_fk(String book_id_fk) {
        this.book_id_fk = book_id_fk;
    }

    public String getT_sale_record_id_fk() {
        return t_sale_record_id_fk;
    }

    public void setT_sale_record_id_fk(String t_sale_record_id_fk) {
        this.t_sale_record_id_fk = t_sale_record_id_fk;
    }

    public String getTrade_sum() {
        return trade_sum;
    }

    public void setTrade_sum(String trade_sum) {
        this.trade_sum = trade_sum;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public SaleRecord getSaleRecord() {
        return saleRecord;
    }

    public void setSaleRecord(SaleRecord saleRecord) {
        this.saleRecord = saleRecord;
    }
}
