package bzu.aa;
 
public class Vehicle 
{
	protected int capacity;
	//无参构造器
	public Vehicle()
	{
		capacity=2;
		System.out.println("执行交通工具类的无参构造方法。");
	}
	public Vehicle(int capacity)
	{
		//通过super层层调用构造器（如果父类构造器有参必须这么做）
		super(); 
		this.capacity=capacity;
		System.out.println("执行交通工具的有参构造方法。");
	}
	//setter和getter方法
	public int getCapacity() 
	{
		return capacity;
	}
	public void setCapacity(int capacity) 
	{
		this.capacity = capacity;
	}
	public void print(){
		System.out.println("Vehicle的capacity为："+capacity);
	}
}
