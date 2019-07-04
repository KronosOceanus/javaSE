package dao;

import entity.SaleRecord;

import java.util.ArrayList;
import java.util.Collection;

public class SaleRecordDaoImpl extends CommonDaoImpl implements SaleRecordDao {

    @Override
    public Collection<SaleRecord> findAll() throws Exception {
        String sql = "select * from t_sale_record";
        return getDatas(sql,new ArrayList(),SaleRecord.class);
    }

    @Override
    public Collection<SaleRecord> findByDate(String date) throws Exception {
        String sql = "select * from t_sale_record sr where sr.record_date like '" +
                date + "%'";
        return getDatas(sql,new ArrayList(),SaleRecord.class);
    }

    @Override
    public SaleRecord findById(String id) throws Exception {
        String sql = "select * from t_sale_record sr where sr.id = '" + id + "'";
        return getSingle(sql);
    }

    @Override
    public SaleRecord addOrUpdate(SaleRecord sr) throws Exception {
        boolean flag = false;
        String sql = null;
        String id = null;
        Collection<SaleRecord> saleRecords = findAll();
        for (SaleRecord saleRecord : saleRecords){
            if(saleRecord.getRecord_date().equals(sr.getRecord_date())){
                flag = true;
                id = saleRecord.getId();
            }
        }
        if (flag){
            sql = "update t_sale_record sr set sr.record_date = '" + sr.getRecord_date() +
                    "' where sr.record_date = '" + sr.getRecord_date() + "'";
            getJDBCExector().executeUpdate(sql);
        }else {
            sql = "insert into t_sale_record values(null,'" + sr.getRecord_date() + "')";
            id = String.valueOf(getJDBCExector().executeUpdate(sql));
        }
        return findById(id);
    }

    private SaleRecord getSingle(String sql) throws Exception{
        SaleRecord result = null;
        for (Object s : getDatas(sql, new ArrayList(),SaleRecord.class)){
            result = (SaleRecord)s;
        }
        return result;
    }
}
