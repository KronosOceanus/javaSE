package t;
/**
 *@author cs
 *@version 1.0
 */
public class Teacher
{
	/**
	 *@param name ����
	 *@param teaYear ��ְ���
	 */
	private String name;
	private int teaYear;
	public Teacher(String n,int t)
	{
		this.name=n;
		this.teaYear=t;
		System.out.println(n+"\n"+t+"\n--------------");
	}
	//setter��getter����
	public void setName(String n)
	{
		this.name=n;
	}
	public String getName()
	{
		return this.name;
	} //
	public void setTeaYear(int a)
	{
		this.teaYear=a;
	}
	public int getTeaYear()
	{
		return this.teaYear;
	}
	//������ʦ��Ϊ
	 public void teach()
	{
		System.out.println(this.name+"�ڽ�ѧ��");
	}
}
