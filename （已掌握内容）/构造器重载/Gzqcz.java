package cs; //����
import static java.lang.System.*; //����System����ȫ��s����
public class Gzqcz //����������
{
	private String name;
	private int age;
	private int weight;
	public Gzqcz(String name,int age)
	{
		this.name=name;
		this.age=age;
	}
	public Gzqcz(String name,int age,int weight)
	{
		this(name,age); //������
		this.weight=weight;
	}
	public static void main(String[] args)
	{
		Gzqcz tc=new Gzqcz("��˧",19,57);
		out.println(""+tc.name+":"+tc.age+"���� "+tc.weight+"kg");
	}
}
