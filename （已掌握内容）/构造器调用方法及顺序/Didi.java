class Father
{
	public Father()
	{
		System.out.println("1!"); //Didi��Ӹ���Ĺ�����
	}
}
class Son extends Father
{
	public Son(String name) //Son��һ������������
	{
		System.out.println("c1 "+name);
	}
	public Son(String name,int age) //Son��������������
	{
		this(name); //this�������ع�������һ��������
		System.out.println("c2 "+age+" years!");
	}
}
public class Didi extends Son
{
	public Didi() //Didi�๹����
	{
		super("����!",18); //super�����丸�๹����
		System.out.println("c3!");
	}
	public static void main(String[] args)
	{
		new Didi();
	}
}
		