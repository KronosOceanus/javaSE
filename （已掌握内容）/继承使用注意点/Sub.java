class Base //���Ծ�����Ҫ�ڸ��๹�����е��ý���������д�ķ���
{
	public Base()
	{
		test(); //�������е���test���������౻��д�ķ������ȣ�
	}
	public void test()
	{
		System.out.println("���ཫ����д�ķ�����");
	}
}
public class Sub extends Base
{
	private String name;
	public void test()
	{
		System.out.println("������д����ķ�����");
		System.out.println(name); //��ʱnameΪnull
	}
	public static void main(String[] args)
	{
		Sub s=new Sub(); //����Subʵ�����ȵ���Base������ִ��test�������ٵ���Sub����������Subʵ��
	}
}