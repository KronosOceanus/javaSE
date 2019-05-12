package cs;

public class Car extends SpeedMeter
{
	public double getR()
	{
		return 0.28;
	}
	public static void main(String[] args)
	{
		Car c=new Car();
		//建立转速
		c.setTurnRate(15);
		//输出速度
		System.out.println(c.getSpeed());
	}
}