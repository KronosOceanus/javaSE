import cs.Output;
import cs.Printer;
import cs.Product;

public class Main
{
    public static void main(String[] args)
    {
		//������ʵ��Ϊ�ӿ�����
        Output p = new Printer();
        p.getData("java");
        p.getData("i love you!");
        p.out();
        p.print("java","shit!");
        p.test();
        Product pr = new Printer();
        System.out.println(pr.getProduceTime());
        Object obj = pr;
    }
}
