import java.util.*;

public class Main {

    public static void main(String[] args){
        Collection books = new ArrayList();
        books.add("������java EE��ҵӦ��ʵս");
        books.add("���java����");
        books.add("���android����");
        //ͨ��Predicate��Ŀ�����ͣ�����������
        //removeIf����ɾ�������з���������Ԫ��
        //lambda���ʽΪ����
        books.removeIf(ele -> ((String)ele).length() < 10);
        for(Object obj:books)
        {
            String book = (String)obj;
            System.out.println(book);
        }
    }
}
		