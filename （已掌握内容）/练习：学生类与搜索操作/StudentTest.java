class Student
{
	private String name;
	private int age;
	private char gender;
	private String phone;
	public Student(){};
	public Student(String n,int a,char g,String p) //构造器中完成赋值并输出
	{
		this.name=n;
		this.age=a;
		this.gender=g;
		this.phone=p;
		System.out.println(n+"\n"+a+"\n"+g+"\n"+p+"\n-----------------");
	} //setter和getter方法
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
	} //描述学生行为的方法
	public void eat()
	{
		System.out.println("吃！");
	}
	public void drink()
	{
		System.out.println("喝！");
	}
	public void play()
	{
		System.out.println("玩！");
	}
	public void sleep()
	{
		System.out.println("睡！");
	}
}
public class StudentTest
{
	public static void search(Student[] sz) //搜索方法
	{
		int n=0;
		for(int i=0;i<sz.length;i++)
		{
		    if(sz[i].getName().equals("高思敏！"))
			{
				n++;
				System.out.println("找到高思敏了！他的年龄是……"+sz[i].getAge()+"岁！");
			}
		}
		if(n==0)
			System.out.println("查找的人不存在！");
	}
	public static void main(String[] args)
	{
		Student s=new Student("蔡帅！",19,'M',"15513266552!");
		s.sleep();
		s.setAge(20); //通过调用setter方法操作private变量
		System.out.println("一年后的年龄是……"+s.getAge()+"岁！\n-----------------");
		//定义引用变量数组并初始化
		Student[] sz={new Student("高思敏！",18,'M',"123!"),new Student("檀赛！",18,'M',"321!")};
		StudentTest.search(sz);
	}
}