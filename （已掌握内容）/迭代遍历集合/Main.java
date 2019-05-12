import java.util.*;

public class Main {

    public static void main(String[] args){
        Collection books = new ArrayList();
        books.add("轻量级java EE企业应用实战");
        books.add("疯狂java讲义");
        books.add("疯狂android讲义");
        Iterator it = books.iterator();
        while(it.hasNext())
        {
            String book = (String)it.next();
            System.out.println(book);
            if(book.equals("疯狂java讲义"))
            {
                it.remove();
                /**
                 * 下面代码错误，不能再迭代中改变集合元素
                 * books.remove(book);
                 */
            }
        }
    }
}
		