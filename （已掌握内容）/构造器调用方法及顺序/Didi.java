class Father
{
	public Father()
	{
		System.out.println("1!"); //Didi间接父类的构造器
	}
}
class Son extends Father
{
	public Son(String name) //Son类一个参数构造器
	{
		System.out.println("c1 "+name);
	}
	public Son(String name,int age) //Son两个参数构造器
	{
		this(name); //this调用重载构造器（一个参数）
		System.out.println("c2 "+age+" years!");
	}
}
public class Didi extends Son
{
	public Didi() //Didi类构造器
	{
		super("政狗!",18); //super调用其父类构造器
		System.out.println("c3!");
	}
	public static void main(String[] args)
	{
		new Didi();
	}
}
		