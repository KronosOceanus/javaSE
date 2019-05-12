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
	//getter和setter方法
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
	//重写equals方法
	public boolean equals(Object obj)
	{
		//如果两个是同一个对象
		if(this==obj) 
			return true;
		//如果该对象不为null，且类别相同
		//（用Object类的getClass()方法返回类别）（Person.class代表Person类）
		if(obj!=null&&obj.getClass()==Person.class)
		{
			//将Object类强制转换为Person类
			//使之可以使用Person类的getIdCard方法
			Person pobj=(Person)obj;
			//比较两个类的idCard是否相同
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
		Person p1=new Person("蔡帅","14273119990507541");
		Person p2=new Person("帅帅！","14273119990507541");
		Person p3=new Person("蔡帅","1427311999");
		System.out.println(p1.equals(p2)+","+p2.equals(p3)+","+p1.equals(p3));
	}
}
	
	