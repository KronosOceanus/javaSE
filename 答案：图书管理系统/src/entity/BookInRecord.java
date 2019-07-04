package entity;

//所有记录
public class BookInRecord extends ValueObject {
    //书W，入库日期W，入库数量
    private String book_id_fk;
    private String t_in_record_id_fk;
    private String in_sum;

    public String getBook_id_fk() {
        return book_id_fk;
    }

    public void setBook_id_fk(String book_id_fk) {
        this.book_id_fk = book_id_fk;
    }

    public String getT_in_record_id_fk() {
        return t_in_record_id_fk;
    }

    public void setT_in_record_id_fk(String t_in_record_id_fk) {
        this.t_in_record_id_fk = t_in_record_id_fk;
    }

    public String getIn_sum() {
        return in_sum;
    }

    public void setIn_sum(String in_sum) {
        this.in_sum = in_sum;
    }
}
