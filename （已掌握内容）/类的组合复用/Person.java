class Arm
{
	public void wave()
	{
		System.out.println("挥动手臂！");
	}
}
class Leg
{
	public void kick()
	{
		System.out.println("抬高双腿！");
	}
}
public class Person
{
	private Arm a; //复用Arm类和Leg类
	private Leg l; //将Person部分类组合到Person类
	public Person(Arm a,Leg l) //Person构造器，以组成Person的部分为参数
	{
		this.a=a; //初始化Person部分类的实例
		this.l=l;
	}
	public void run()
	{
		a.wave(); //引用两个部分类的方法
		l.kick();
		System.out.println("我在奔跑！");
	}
	public static void main(String[] args)
	{
		Arm a1=new Arm(); //先创建部分类实例
		Leg l1=new Leg();
		Person p=new Person(a1,l1); //创建Person类实例
		p.run(); //Person类实例活动
	}
}