package database;

import java.util.ArrayList;

public class SaleRecord extends ValueObject{

    //销售日期（对应多个销售记录）
    private String record_date;
    //销售记录，销售总数量，总价格，所有书名
    private ArrayList<BookSaleRecord> bookSaleRecords;
    private int amount;
    private double totalPrice;
    private String[] bookNames;

    public SaleRecord(){};
    public SaleRecord(String recordDate){
        this.record_date = recordDate;
    }

    public String getRecord_date() {
        return record_date;
    }

    public void setRecord_date(String record_date) {
        this.record_date = record_date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ArrayList<BookSaleRecord> getBookSaleRecords() {
        return bookSaleRecords;
    }

    public void setBookSaleRecords(ArrayList<BookSaleRecord> bookSaleRecords) {
        this.bookSaleRecords = bookSaleRecords;
    }

    public String[] getBookNames() {
        return bookNames;
    }

    public void setBookNames(String[] bookNames) {
        this.bookNames = bookNames;
    }
}
