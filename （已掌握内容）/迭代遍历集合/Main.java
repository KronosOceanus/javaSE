import java.util.*;

public class Main {

    public static void main(String[] args){
        Collection books = new ArrayList();
        books.add("������java EE��ҵӦ��ʵս");
        books.add("���java����");
        books.add("���android����");
        Iterator it = books.iterator();
        while(it.hasNext())
        {
            String book = (String)it.next();
            System.out.println(book);
            if(book.equals("���java����"))
            {
                it.remove();
                /**
                 * ���������󣬲����ٵ����иı伯��Ԫ��
                 * books.remove(book);
                 */
            }
        }
    }
}
		