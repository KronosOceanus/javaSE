class Date
{
	int a;
	int b;
}
public class Exchange
{
	public static void swap(Date x)
	{
		int t=x.a;
		x.a=x.b;
		x.b=t;
	}
	public static void main(String[] args)
	{
	    Date dw=new Date();
		dw.a=10;
		dw.b=6;
		swap(dw); //传入对象
		System.out.println(""+dw.a+"            "+dw.b);
	}
}