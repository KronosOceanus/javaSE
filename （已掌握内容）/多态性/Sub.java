class Base
{
	public int a=6;
	public void info1()
	{
		System.out.println("����1��");
	}
	public void test()
	{
		System.out.println("�����н�����д�ķ�����");
	}
}
public class Sub extends Base
{
	public String a="java!";
	public void info2()
	{
		System.out.println("����2��");
	}
	public void test()
	{
		System.out.println("������д����ķ�����");
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
	    *����ʱ����ΪBase���ж���û��info2�������Ա���
		bsc.info2();
		*/
		bsc.test();
	}
}