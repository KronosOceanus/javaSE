package bzu.aa;
 
public class Car extends Vehicle
{
	protected int speed;
	public Car()
	{
		speed=0;
		System.out.println("ִ����������޲ι��췽����");
	}
	public Car(int speed)
	{
		super(6); //���ø��๹��������capacity
		this.speed=speed;
		System.out.println("ִ����������вι��췽����");
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
		System.out.println("????????????��???????");
	}
	public Car(int speed)
	{
		super(6); //???????????????capacity
		this.speed=speed;
		System.out.println("???????????�Ӧ�???????");
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
		super.print(); //���ø���print����
		System.out.println("Car��capacity:"+capacity
				+",Car��speed:"+speed);
	}
}