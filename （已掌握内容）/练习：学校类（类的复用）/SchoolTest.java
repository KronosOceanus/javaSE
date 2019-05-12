package schooltest;
/**
 *@author cs
 *@version 1.0
 */
import school.*;
import p.*;
import t.*;
import s.*;
public class SchoolTest
{
	
	public static void main(String[] args)
	{
		//创建Student实例并输出
		Student p1=new Student("蔡帅！",19,'M',"123！");
		//以该Student实例为参数创建Person实例
		Person p11=new Person(p1);
		//以该Person实例为参数创建学校组成部分实例
		School xs=new School(p11);
		//Teacher实例的第二个参数为任职年份
		Teacher p2=new Teacher("某老师！",15);
		//构造器重载
		Person p22=new Person(p2);
		School ls=new School(p22);
		p1.drink();
		p11.eat();
		p2.teach();
		p22.eat();
	}
}