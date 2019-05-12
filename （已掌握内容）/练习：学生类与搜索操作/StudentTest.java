class Student
{
	private String name;
	private int age;
	private char gender;
	private String phone;
	public Student(){};
	public Student(String n,int a,char g,String p) //����������ɸ�ֵ�����
	{
		this.name=n;
		this.age=a;
		this.gender=g;
		this.phone=p;
		System.out.println(n+"\n"+a+"\n"+g+"\n"+p+"\n-----------------");
	} //setter��getter����
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
	} //����ѧ����Ϊ�ķ���
	public void eat()
	{
		System.out.println("�ԣ�");
	}
	public void drink()
	{
		System.out.println("�ȣ�");
	}
	public void play()
	{
		System.out.println("�棡");
	}
	public void sleep()
	{
		System.out.println("˯��");
	}
}
public class StudentTest
{
	public static void search(Student[] sz) //��������
	{
		int n=0;
		for(int i=0;i<sz.length;i++)
		{
		    if(sz[i].getName().equals("��˼����"))
			{
				n++;
				System.out.println("�ҵ���˼���ˣ����������ǡ���"+sz[i].getAge()+"�꣡");
			}
		}
		if(n==0)
			System.out.println("���ҵ��˲����ڣ�");
	}
	public static void main(String[] args)
	{
		Student s=new Student("��˧��",19,'M',"15513266552!");
		s.sleep();
		s.setAge(20); //ͨ������setter��������private����
		System.out.println("һ���������ǡ���"+s.getAge()+"�꣡\n-----------------");
		//�������ñ������鲢��ʼ��
		Student[] sz={new Student("��˼����",18,'M',"123!"),new Student("̴����",18,'M',"321!")};
		StudentTest.search(sz);
	}
}