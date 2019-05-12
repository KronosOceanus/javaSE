class Vehicle
{
    /**
	 *@param capacity �ؿ���
	 *@param speed �ٶ�
	 *protected����ʹ������Է��ʺͲ���
	 */
	protected int capacity;
	protected int speed;
	//�޲���������
	public Vehicle(){}
	//����������ͨ�������ͣ��ؿ������ٶȣ�
	public Vehicle(Car c,int ca,int s)
	{
		c.capacity=ca;
		c.speed=s;
	}
	public Vehicle(Plane p,int ca,int s)
	{
		p.capacity=ca;
		p.speed=s;
	}
	//��ͨ���߷���
	public String travel()
	{
		return "������ʻ��";
	}
	public void open()
	{
		System.out.println("���ţ�");
	}
	public void close()
	{
	    System.out.println("���ţ�");
	}
}
class Car extends Vehicle
{
	//���ڸ���ı���name���������ƣ�
	private static String name="����";
	//getter����
	public String getName()
	{
		return this.name;
	}
	//
	public void start()
	{
		open();
		close();
		System.out.println(Car.name+""+travel()+"\n-----------");
	}
	public void print()
	{
	    System.out.println(name+"\n�ؿ���Ϊ"+capacity+"\n�ٶ�Ϊ"+speed+"km/h\n-----------");
	}
}
class Plane extends Vehicle
{
	private static String name="�ɻ�";
	//getter����
	public String getName()
	{
		return this.name;
	}
	//������д���෽��
    public String travel()
	{
		return "���ڷ��У�";
	}
	public void start()
	{
		open();
		close();
		System.out.println(Plane.name+""+travel()+"\n-----------");
	}
	public void print()
	{
		System.out.println(name+"\n�ؿ���Ϊ"+capacity+"\n�ٶ�Ϊ"+speed+"km/h\n-----------");
	}
}
public class VehicleTest
{
	public static void main(String[] args)
	{
		//�ȴ��������ٴ�������
		Car c=new Car();
		Vehicle c0=new Vehicle(c,5,40);
		c.start();
		c.print();
		Plane p=new Plane();
		Vehicle p0=new Vehicle(p,400,800);
		p.start();
		p.print();
	}
}
	
		