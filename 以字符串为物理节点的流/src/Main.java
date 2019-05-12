import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {

    public static void main(String[] args) {

        String src = "����������һ���Ҹ�����\n"+
                "ι��������������\n"+
                "��������,������ʳ���߲�\n"+
                "����һ�����ӣ��泯�󺣣���ů����\n"+
                "��������,��ÿһ������ͨ��\n"+
                "���������ҵ��Ҹ�\n";
        char[] buffer =new char[32];	 
        int hasRead = 0;
        try(
				//���������û�й���
                StringReader sr = new StringReader(src);
                StringWriter sw = new StringWriter();
                )
        {
            while((hasRead = sr.read(buffer)) > 0){
                sw.write(buffer,0,hasRead);
                System.out.println(new String(buffer,0,hasRead));
            }

            System.out.println("====������sw�ַ����ڵ��������====");
            System.out.println(sw.toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
