package bzu.aa;
 
public class Car extends Vehicle
{
	protected int speed;
	public Car()
	{
		speed=0;
		System.out.println("执行汽车类的无参构造方法。");
	}
	public Car(int speed)
	{
		super(6); //调用父类构造器构造capacity
		this.speed=speed;
		System.out.println("执行汽车类的有参构造方法。");
	}
	public int speedup(int n)
	{
		for(;n>0;n--){
			speed+=10;
		}
		return speed;
	}package bzu.aa;
 
public class Car extends Vehicle
{
	protected int speed;
	public Car()
	{
		speed=0;
		System.out.println("????????????ι???????");
	}
	public Car(int speed)
	{
		super(6); //???????????????capacity
		this.speed=speed;
		System.out.println("???????????вι???????");
	}
	public int speedup(int n)
	{
		for(;n>0;n--){
			speed+=10;
		}
		return speed;
	}
	public int speeddown(int n)
	{
		for(;n>0;n--){
			speed-=15;
		}
		return speed;
	}
	public void print()
	{
		super.print(); //调用父类print方法
		System.out.println("Car的capacity:"+capacity
				+",Car的speed:"+speed);
	}
}