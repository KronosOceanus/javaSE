package database;

public class InRecord extends ValueObject{
    //入库日期（对应多个入库记录）
    private String record_date;

    public String getRecord_date() {
        return record_date;
    }

    public void setRecord_date(String record_date) {
        this.record_date = record_date;
    }
}
