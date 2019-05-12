import java.util.*;

public class Main {

    public static void main(String[] args){
        Collection books = new ArrayList();
        books.add("轻量级java EE企业应用实战");
        books.add("疯狂java讲义");
        books.add("疯狂android讲义");
        //通过Predicate（目标类型）来遍历集合
        //removeIf批量删除集合中符合条件的元素
        //lambda表达式为条件
        books.removeIf(ele -> ((String)ele).length() < 10);
        for(Object obj:books)
        {
            String book = (String)obj;
            System.out.println(book);
        }
    }
}
		