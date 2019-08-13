import org.apache.commons.dbutils.handlers.*;

import java.util.List;
import java.util.Map;

public class Main {

    private static JDBCExector je;
    private Main() throws Exception {
        je = JDBCExector.getInstance();
    }
    public static void main(String[] args) throws Exception {
        Main m = new Main();

        String sql = "select * from t_book";
        List<Object[]> list = je.query(sql,new ArrayListHandler());
        for (Object[] oa : list){
            for (Object o : oa){
                System.out.println(o);
            }
        }
        System.out.println("================================");

        Book book = je.query(sql,new BeanHandler<>(Book.class));
        System.out.println(book);
        System.out.println("================================");

        List<Book> bookList = je.query(sql,new BeanListHandler<Book>(Book.class));
        for (Book b : bookList){
            System.out.println(b);
        }
        System.out.println("================================");

        List<String> colList = je.query(sql,new ColumnListHandler<>("book_name"));
        for (Object o : colList){
            System.out.println(o);
        }
        System.out.println("================================");

        sql = "select book_intro from t_book";
        String s = je.query(sql,new ScalarHandler<String>());
        System.out.println(s);
        System.out.println("================================");

        sql = "select * from t_book";
        Map<String,Object> map = je.query(sql,new MapHandler());
        for (String str : map.keySet()){
            System.out.println(str + "====" + map.get(str));
        }
        System.out.println("================================");

        List<Map<String,Object>> mapList = je.query(sql,new MapListHandler());
        for (Map<String,Object> mp : mapList){
            for (String st : mp.keySet()){
                System.out.println(st + "----" + mp.get(st));
            }
            System.out.println("------------------------");
        }
    }
}
