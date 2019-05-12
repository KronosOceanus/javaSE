package ky;

public class JKTest
{
	public static void main(String[] args)
	{
		Integer a=10;
		//不同包直接访问接口成员
		System.out.println(a+cs.Output.M);
		System.out.println(cs.Output.staticTest());
	}
}