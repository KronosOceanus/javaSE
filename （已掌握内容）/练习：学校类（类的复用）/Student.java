package s;
/**
 *@author cs
 *@version 1.0
 */
public class Student
{
	/**
	 *@param name ����
	 *@param age ����
	 *@param gender �Ա�
	 *@param phone �ֻ���
	 */
	private String name;
	private int age;
	private char gender;
	private String phone;
	public Student(String n,int a,char g,String p) //����������ɸ�ֵ�����
	{
		this.name=n;
		this.age=a;
		this.gender=g;
		this.phone=p;
		System.out.println(n+"\n"+a+"\n"+g+"\n"+p+"\n-----------------");
	} 
	//setter��getter����
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
	//����ѧ����Ϊ�ķ���
	public void drink()
	{
		System.out.println(this.name+"�ںȣ�");
	}
	public void play()
	{
		System.out.println(this.name+"���棡");
	}
	public void sleep()
	{
		System.out.println(this.name+"��˯��");
	}
}