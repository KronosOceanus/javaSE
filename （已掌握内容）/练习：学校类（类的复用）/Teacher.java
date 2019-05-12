package t;
/**
 *@author cs
 *@version 1.0
 */
public class Teacher
{
	/**
	 *@param name 名字
	 *@param teaYear 在职年份
	 */
	private String name;
	private int teaYear;
	public Teacher(String n,int t)
	{
		this.name=n;
		this.teaYear=t;
		System.out.println(n+"\n"+t+"\n--------------");
	}
	//setter和getter方法
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
	//描述老师行为
	 public void teach()
	{
		System.out.println(this.name+"在教学！");
	}
}
