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
		//����ת��
		c.setTurnRate(15);
		//����ٶ�
		System.out.println(c.getSpeed());
	}
}