package bzu.bb;
 
import bzu.aa.Car;
 
public final class Bus extends Car
{
    int capacity;
	public Bus()
	{
		capacity=20;
		System.out.println("执行公交车类的无参构造方法。");
	}
	public Bus(int capacity) 
	{
		super(30);
		this.capacity=capacity;
		System.out.println("执行公交车类的有参构造方法。");
	}
	public void print()
	{
		super.print(); //层层调用输出
		System.out.println("Bus的speed:"+speed
				+",Bus的capacity:"+capacity+",父类的capacity:"+super.capacity);
	}
}