package p;
/**
 *@author cs
 *@version 1.0
 */
import t.*;
import s.*;
public class Person
{
	/**
	*@param Teacher ��ʦ��Ա
	 *@param t0 ѧ����Ա
	 *@param name ���֣�����Ϊ���ã�
	 *@param age ����
	 */
	private Teacher t0;
	private Student s0;
	private String name;
	private int age;
	//����������
	public Person(String n,int a)
	{
		this.name=n;
		this.age=a;
		System.out.println(n+"\n"+a+"\n---------------");
	}
	public Person(Teacher t0)
	{
		this.t0=t0;
		this.name=t0.getName();
		System.out.println("��ʦ����:"+t0.getName()+"\n��ְ���:"+t0.getTeaYear()+"\n---------------");
	}
	public Person(Student s0)
	{
		this.s0=s0;
		this.name=s0.getName();
		System.out.println("ѧ������:"+s0.getName()+"\n����:"+s0.getAge()+"\n---------------");
	}
	//setter��getter����
	public void setT0(Teacher t0)
	{
		this.t0=t0;
	}
	public Teacher getT0()
	{
		return this.t0;
	} //
	public void setS0(Student s0)
	{
		this.s0=s0;
	}
	public Student getS0()
	{
		return this.s0;
	} //
	public void setName(String n)
	{
		this.name=n;
	}
	public String getName()
	{
		return this.name;
	} //
	public void setAge(int a)
	{
		this.age=a;
	}
	public int getAge()
	{
		return this.age;
	} //
    public void eat()
	{
		System.out.println(this.name+"�ڳԣ�");
	}
}