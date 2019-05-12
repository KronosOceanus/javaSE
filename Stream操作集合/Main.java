import java.util.*;

public class Main {

    public static void main(String[] args) {
        Collection books = new ArrayList();
        books.add("������java EE��ҵӦ��ʵս");
        books.add("���java����");
        books.add("���android����");

        //����Stream��������������
        /**
         * ��stream����������ת��ΪStream����
         * ��filter�������˵�������������
         * ��count���������������Ԫ�ص�����
         */
        System.out.println(books.stream().filter(ele -> ((String) ele).contains("���")).count()+"\n");

        /**
         * ��mapToInt������Streamת��ΪIntStream
         * �����е�Ԫ����lambda���ʽ����һһ�Ի�
         * ��ΪmapToXxx���м䷽�������Կ��Լ�������forEach����
         *System.out::println����println������lambda���ʽ�������ã�
         */
        books.stream().mapToInt(ele -> ((String)ele).length()).forEach(System.out::println);
    }
}