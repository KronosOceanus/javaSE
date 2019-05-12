class Father
{
	public int age=50;
}
class Son extends Father
{
	private int age=19;
}
public class HideTest
{
	public static void main(String[] args)
	{
		Son s=new Son();
		/**
		*访问private变量报错
		System.out.println(s.age);
		*/
		System.out.println(((Father)s).age); //强制转换s为Father型使之可以访问Father类中的age变量
	}
}