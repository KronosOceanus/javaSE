package bzu.bb;
 
import bzu.aa.Car;
 
public final class Bus extends Car
{
    int capacity;
	public Bus()
	{
		capacity=20;
		System.out.println("ִ�й���������޲ι��췽����");
	}
	public Bus(int capacity) 
	{
		super(30);
		this.capacity=capacity;
		System.out.println("ִ�й���������вι��췽����");
	}
	public void print()
	{
		super.print(); //���������
		System.out.println("Bus��speed:"+speed
				+",Bus��capacity:"+capacity+",�����capacity:"+super.capacity);
	}
}