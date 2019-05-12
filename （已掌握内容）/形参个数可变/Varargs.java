public class Varargs
{
	public static void test(int a,String... books) //String...表示形参个数可变（但个数可变的形参必须处于最后）
	{
		for(String tmp:books)
		{
			System.out.println(tmp);
		}
		System.out.println(a);
	}
	public static void main(String[] args)
	{
		test(5,"shit","woaini","zhenxiang");
	}
}