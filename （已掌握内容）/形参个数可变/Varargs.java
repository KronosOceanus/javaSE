public class Varargs
{
	public static void test(int a,String... books) //String...��ʾ�βθ����ɱ䣨�������ɱ���βα��봦�����
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