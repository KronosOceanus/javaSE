class Father
{
	public int age=50;
}
public class Son extends Father
{
	private int age=19; 
	public void putAgeS()
	{
		System.out.println(age);
	}
	public void putAgeF()
	{
		System.out.println(super.age); //super限定age为父类中的age
	}
	public static void main(String[] args)
	{
		Son s=new Son();
		s.putAgeF();
		s.putAgeS(); //调用不同方法访问子类父类中同名变量
	}
}