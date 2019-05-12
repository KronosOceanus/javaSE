package cs;

public class Triangle extends Shape
{
	private double a,b,c;
	public Triangle(double a,double b,double c)
	{
		this.setSides(a,b,c);
	}
	public void setSides(double a,double b,double c)
	{
		if(a+b>c&&b+c>a&&a+c>b)
		{
			this.a=a;
			this.b=b;
			this.c=c;
		}
	}
	//子类重写父类中抽象方法
	public String getType()
	{
		return "Triangle";
	}
	public double getC()
	{
		return a+b+c;
	}
	public static void main(String[] args)
	{
		//多态（先判断父类中是否有getType和getC方法，如果有，优先执行子类重写的方法，没有则编译失败）
		Shape s=new Triangle(3,4,5);
		//确保父类中有getType和getC方法，使实例能调用子类重写的方法
		System.out.println("该"+s.getType()+"的周长为："+s.getC());
	}
}