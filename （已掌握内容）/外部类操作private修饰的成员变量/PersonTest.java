class Person
{
	private int age; //private���γ�Ա����
	private String name;
	public void setName(String name) //���������ķ���
	{
		if(name.length()>=3&&name.length()<=6) //�жϺ�������
			this.name=name;
		else
			System.out.println("�������ֲ�����Ҫ��");
	}
	public String getName() //����������ֲ�����Ҫ�󷵻س�ֵ
	{
		return this.name;
	}
	public void setAge(int age)
	{
		if(age<=100&&age>=0)
			this.age=age;
		else
			System.out.println("�������䲻����");
	}
	public int getAge()
	{
		return this.age;
	}
}
public class PersonTest //�ⲿ�����������private���εĳ�Ա����
{
	public static void main(String[] args)
	{
		Person p=new Person(); //����ʵ��
		p.setAge(1000); //private���εĳ�Ա����ͨ��setter���ʸ�ֵ
		System.out.println(p.getAge()); //ͨ��getter���
		p.setAge(10); 
		System.out.println(p.getAge());
	}
}