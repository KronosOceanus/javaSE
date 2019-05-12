//Collection
import java.util.*;
//Predicate
import java.util.function.*;

public class PredicateTest {

    public static void main(String[] args){
        Collection books = new ArrayList();
        books.add("������java EE��ҵӦ��ʵս");
        books.add("���java����");
        books.add("���android����");

        System.out.println(calAll(books,ele -> ((String)ele).contains("���")));
        System.out.println(calAll(books,ele -> ((String)ele).contains("java")));
    }
    public static int calAll(Collection books, Predicate p)
    {
        int total = 0;
        for(Object obj:books)
        {
            //ͨ��Predicate��test�����ж��Ƿ�����ָ������
            if(p.test(obj))
                total ++;
        }
        return total;
    }
}