package cs;

public class Triangle extends Shape
{
	private double a,b,c;
	public Triangle(double a,double b,double c)
	{
		this.setSides(a,b,c);
	}
	public void setSides(double a,double b,double c)
	{
		if(a+b>c&&b+c>a&&a+c>b)
		{
			this.a=a;
			this.b=b;
			this.c=c;
		}
	}
	//������д�����г��󷽷�
	public String getType()
	{
		return "Triangle";
	}
	public double getC()
	{
		return a+b+c;
	}
	public static void main(String[] args)
	{
		//��̬�����жϸ������Ƿ���getType��getC����������У�����ִ��������д�ķ�����û�������ʧ�ܣ�
		Shape s=new Triangle(3,4,5);
		//ȷ����������getType��getC������ʹʵ���ܵ���������д�ķ���
		System.out.println("��"+s.getType()+"���ܳ�Ϊ��"+s.getC());
	}
}