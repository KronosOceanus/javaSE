class Plant
{
	public String name;
	public void info1()
	{
		System.out.println("I am a plant!");
	}
}
class Fruit extends Plant
{
    public int weight;
    public void info2()
	{
		this.name="orange"; //使用父类变量name
		System.out.println("I am a fruit!\nMy name is "+name+".");
		System.out.println("and my weight is "+weight+" kg.");
	}
}
public class Orange extends Fruit
{
	public static void main(String[] args)
	{
		Orange o=new Orange();
		o.weight=100; //使用父类变量weight
		o.info1(); //实例调用方法
		o.info2(); //调用父类方法
	}
} //不能直接使用间接父类的变量和方法