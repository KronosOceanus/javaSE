public class ThisReturn
{
	public int age;
	public ThisReturn grow() //���췽��
	{
		age++;
		return this;
	}
	public static void main(String[] args)
	{
		ThisReturn rt=new ThisReturn();
		rt.grow()
		   .grow()
		    .grow(); //���췽����this������ֵ������������ͬһ������
		System.out.println(rt.age);
	}
}