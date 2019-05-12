package s;
/**
 *@author cs
 *@version 1.0
 */
public class Student
{
	/**
	 *@param name 名字
	 *@param age 年龄
	 *@param gender 性别
	 *@param phone 手机号
	 */
	private String name;
	private int age;
	private char gender;
	private String phone;
	public Student(String n,int a,char g,String p) //构造器中完成赋值并输出
	{
		this.name=n;
		this.age=a;
		this.gender=g;
		this.phone=p;
		System.out.println(n+"\n"+a+"\n"+g+"\n"+p+"\n-----------------");
	} 
	//setter和getter方法
	public void setName(String name)
	{
		this.name=name;
	}
	public String getName()
	{
		return this.name;
	}//
	public void setAge(int age)
	{
		this.age=age;
	}
	public int getAge()
	{
		return this.age;
	}//
	public void setGender(char gender)
	{
		this.gender=gender;
	}
	public char getGender()
	{
		return this.gender;
	}//
	public void setPhone(String phone)
	{
		this.phone=phone;
	}
	public String getPhone()
	{
		return this.phone;
	}
	//描述学生行为的方法
	public void drink()
	{
		System.out.println(this.name+"在喝！");
	}
	public void play()
	{
		System.out.println(this.name+"在玩！");
	}
	public void sleep()
	{
		System.out.println(this.name+"在睡！");
	}
}