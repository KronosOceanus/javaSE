public class ThisReturn
{
	public int age;
	public ThisReturn grow() //构造方法
	{
		age++;
		return this;
	}
	public static void main(String[] args)
	{
		ThisReturn rt=new ThisReturn();
		rt.grow()
		   .grow()
		    .grow(); //构造方法用this作返回值可以连续调用同一个方法
		System.out.println(rt.age);
	}
}