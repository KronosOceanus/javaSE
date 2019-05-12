class Person
{
	private int age; //private修饰成员变量
	private String name;
	public void setName(String name) //建立姓名的方法
	{
		if(name.length()>=3&&name.length()<=6) //判断合理理性
			this.name=name;
		else
			System.out.println("输入名字不符合要求！");
	}
	public String getName() //若输入的名字不符合要求返回初值
	{
		return this.name;
	}
	public void setAge(int age)
	{
		if(age<=100&&age>=0)
			this.age=age;
		else
			System.out.println("输入年龄不合理");
	}
	public int getAge()
	{
		return this.age;
	}
}
public class PersonTest //外部类操作其他类private修饰的成员变量
{
	public static void main(String[] args)
	{
		Person p=new Person(); //创建实例
		p.setAge(1000); //private修饰的成员变量通过setter访问赋值
		System.out.println(p.getAge()); //通过getter输出
		p.setAge(10); 
		System.out.println(p.getAge());
	}
}