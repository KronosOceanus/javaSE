class Person
{
	private String name;
	private String idCard;
	public Person(){};
	public Person(String name,String idCard)
	{
		this.name=name;
		this.idCard=idCard;
	}
	//getter��setter����
	public void setName(String name)
	{
		this.name=name;
	}
	public String getName()
	{
		return this.name;
	}
	public void setIdCard(String idCard)
	{
		this.idCard=idCard;
	}
	public String getIdCard()
	{
		return this.idCard;
	}
	//��дequals����
	public boolean equals(Object obj)
	{
		//���������ͬһ������
		if(this==obj) 
			return true;
		//����ö���Ϊnull���������ͬ
		//����Object���getClass()����������𣩣�Person.class����Person�ࣩ
		if(obj!=null&&obj.getClass()==Person.class)
		{
			//��Object��ǿ��ת��ΪPerson��
			//ʹ֮����ʹ��Person���getIdCard����
			Person pobj=(Person)obj;
			//�Ƚ��������idCard�Ƿ���ͬ
			if(this.getIdCard().equals(pobj.getIdCard()))
				return true;
		}
		return false;
	}
}
public class EqualsTest
{
	public static void main(String[] args)
	{
		Person p1=new Person("��˧","14273119990507541");
		Person p2=new Person("˧˧��","14273119990507541");
		Person p3=new Person("��˧","1427311999");
		System.out.println(p1.equals(p2)+","+p2.equals(p3)+","+p1.equals(p3));
	}
}
	
	