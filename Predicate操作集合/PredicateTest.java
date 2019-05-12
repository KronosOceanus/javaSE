//Collection
import java.util.*;
//Predicate
import java.util.function.*;

public class PredicateTest {

    public static void main(String[] args){
        Collection books = new ArrayList();
        books.add("轻量级java EE企业应用实战");
        books.add("疯狂java讲义");
        books.add("疯狂android讲义");

        System.out.println(calAll(books,ele -> ((String)ele).contains("疯狂")));
        System.out.println(calAll(books,ele -> ((String)ele).contains("java")));
    }
    public static int calAll(Collection books, Predicate p)
    {
        int total = 0;
        for(Object obj:books)
        {
            //通过Predicate的test方法判断是否满足指定条件
            if(p.test(obj))
                total ++;
        }
        return total;
    }
}