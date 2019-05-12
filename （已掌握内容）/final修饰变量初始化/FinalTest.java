public class FinalTest
{
	final String name;
	final int a=5;
    static final int b;
	final int c;
	/**
	 *未初始化的变量ch（非法）
	final char ch;
	*/
	{
		name="!!!";
	}
	static
	{
		b=10;
	}
	public FinalTest()
	{
		c=100;
	}
	/**
	 *普通方法不能为final修饰的变量赋值
	public void test()
	{
		b=6;
		ch='a';
	}
	*/
	public static void main(String[] args)
	{
		FinalTest ft=new FinalTest();
		System.out.println(ft.name);
		System.out.println(ft.a);
		System.out.println(FinalTest.b);
		System.out.println(ft.c);
		/**
		 *ch未初始化出错
		System.out.println(ft.ch);
		*/
	}
}