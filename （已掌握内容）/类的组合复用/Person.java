class Arm
{
	public void wave()
	{
		System.out.println("�Ӷ��ֱۣ�");
	}
}
class Leg
{
	public void kick()
	{
		System.out.println("̧��˫�ȣ�");
	}
}
public class Person
{
	private Arm a; //����Arm���Leg��
	private Leg l; //��Person��������ϵ�Person��
	public Person(Arm a,Leg l) //Person�������������Person�Ĳ���Ϊ����
	{
		this.a=a; //��ʼ��Person�������ʵ��
		this.l=l;
	}
	public void run()
	{
		a.wave(); //��������������ķ���
		l.kick();
		System.out.println("���ڱ��ܣ�");
	}
	public static void main(String[] args)
	{
		Arm a1=new Arm(); //�ȴ���������ʵ��
		Leg l1=new Leg();
		Person p=new Person(a1,l1); //����Person��ʵ��
		p.run(); //Person��ʵ���
	}
}