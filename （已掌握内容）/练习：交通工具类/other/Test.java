package bzu.bb;
 
import bzu.aa.Car;
 
public class Test 
{
	public static void main(String[] args) 
	{
		Car car=new Car();
	    car.speedup(5); //用方法赋值变量
		car.print();
		car.speeddown(2);
		car.print();
		Bus bus=new Bus(20);
		bus.print();
	}
}