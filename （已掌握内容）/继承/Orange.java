class Plant
{
	public String name;
	public void info1()
	{
		System.out.println("I am a plant!");
	}
}
class Fruit extends Plant
{
    public int weight;
    public void info2()
	{
		this.name="orange"; //ʹ�ø������name
		System.out.println("I am a fruit!\nMy name is "+name+".");
		System.out.println("and my weight is "+weight+" kg.");
	}
}
public class Orange extends Fruit
{
	public static void main(String[] args)
	{
		Orange o=new Orange();
		o.weight=100; //ʹ�ø������weight
		o.info1(); //ʵ�����÷���
		o.info2(); //���ø��෽��
	}
} //����ֱ��ʹ�ü�Ӹ���ı����ͷ���