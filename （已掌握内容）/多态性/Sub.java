class Base
{
	public int a=6;
	public void info1()
	{
		System.out.println("方法1！");
	}
	public void test()
	{
		System.out.println("父类中将被重写的方法！");
	}
}
public class Sub extends Base
{
	public String a="java!";
	public void info2()
	{
		System.out.println("方法2！");
	}
	public void test()
	{
		System.out.println("子类重写父类的方法！");
	}
	public static void main(String[] args)
	{
		Base bc=new Base();
		System.out.println(bc.a);
		bc.info1();
		bc.test();
		Sub sc=new Sub();
		System.out.println(sc.a);
		sc.info2();
		sc.test();
		Base bsc=new Sub();
		System.out.println(bsc.a);
		bsc.info1();
		/**
	    *编译时类型为Base类判断其没有info2方法所以报错
		bsc.info2();
		*/
		bsc.test();
	}
}