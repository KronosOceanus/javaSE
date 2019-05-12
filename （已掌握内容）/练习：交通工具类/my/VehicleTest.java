class Vehicle
{
    /**
	 *@param capacity 载客量
	 *@param speed 速度
	 *protected修饰使子类可以访问和操作
	 */
	protected int capacity;
	protected int speed;
	//无参数构造器
	public Vehicle(){}
	//构造器（交通工具类型，载客量，速度）
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
	//交通工具方法
	public String travel()
	{
		return "正在行驶！";
	}
	public void open()
	{
		System.out.println("开门！");
	}
	public void close()
	{
	    System.out.println("关门！");
	}
}
class Car extends Vehicle
{
	//属于该类的变量name（即类名称）
	private static String name="汽车";
	//getter方法
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
	    System.out.println(name+"\n载客量为"+capacity+"\n速度为"+speed+"km/h\n-----------");
	}
}
class Plane extends Vehicle
{
	private static String name="飞机";
	//getter方法
	public String getName()
	{
		return this.name;
	}
	//子类重写父类方法
    public String travel()
	{
		return "正在飞行！";
	}
	public void start()
	{
		open();
		close();
		System.out.println(Plane.name+""+travel()+"\n-----------");
	}
	public void print()
	{
		System.out.println(name+"\n载客量为"+capacity+"\n速度为"+speed+"km/h\n-----------");
	}
}
public class VehicleTest
{
	public static void main(String[] args)
	{
		//先创建子类再创建父类
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
	
		