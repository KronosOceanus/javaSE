package bzu.aa;
 
public class Vehicle 
{
	protected int capacity;
	//�޲ι�����
	public Vehicle()
	{
		capacity=2;
		System.out.println("ִ�н�ͨ��������޲ι��췽����");
	}
	public Vehicle(int capacity)
	{
		//ͨ��super�����ù�������������๹�����вα�����ô����
		super(); 
		this.capacity=capacity;
		System.out.println("ִ�н�ͨ���ߵ��вι��췽����");
	}
	//setter��getter����
	public int getCapacity() 
	{
		return capacity;
	}
	public void setCapacity(int capacity) 
	{
		this.capacity = capacity;
	}
	public void print(){
		System.out.println("Vehicle��capacityΪ��"+capacity);
	}
}
