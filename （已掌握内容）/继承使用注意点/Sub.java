class Base //所以尽量不要在父类构造器中调用将被子类重写的方法
{
	public Base()
	{
		test(); //构造器中调用test方法（子类被重写的方法优先）
	}
	public void test()
	{
		System.out.println("父类将被重写的方法！");
	}
}
public class Sub extends Base
{
	private String name;
	public void test()
	{
		System.out.println("子类重写父类的方法！");
		System.out.println(name); //此时name为null
	}
	public static void main(String[] args)
	{
		Sub s=new Sub(); //创建Sub实例，先调用Base构造器执行test方法，再调用Sub构造器创建Sub实例
	}
}