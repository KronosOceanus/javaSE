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
		*����private��������
		System.out.println(s.age);
		*/
		System.out.println(((Father)s).age); //ǿ��ת��sΪFather��ʹ֮���Է���Father���е�age����
	}
}