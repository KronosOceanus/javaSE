public class FinalTest
{
	final String name;
	final int a=5;
    static final int b;
	final int c;
	/**
	 *δ��ʼ���ı���ch���Ƿ���
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
	 *��ͨ��������Ϊfinal���εı�����ֵ
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
		 *chδ��ʼ������
		System.out.println(ft.ch);
		*/
	}
}