package school;
/**
 *@author cs
 *@version 1.0
 */
import p.*;
public class School
{
	/**
	 *@param p0 人变量
	 */
	private Person p0;
	public School(Person p0)
	{
		this.p0=p0;
	}
	//setter和getter方法
	public void setP0(Person p0)
	{
		this.p0=p0;
	}
	public Person getP0()
	{
		return this.p0;
	}
}