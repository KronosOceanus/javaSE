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
		//����Studentʵ�������
		Student p1=new Student("��˧��",19,'M',"123��");
		//�Ը�Studentʵ��Ϊ��������Personʵ��
		Person p11=new Person(p1);
		//�Ը�Personʵ��Ϊ��������ѧУ��ɲ���ʵ��
		School xs=new School(p11);
		//Teacherʵ���ĵڶ�������Ϊ��ְ���
		Teacher p2=new Teacher("ĳ��ʦ��",15);
		//����������
		Person p22=new Person(p2);
		School ls=new School(p22);
		p1.drink();
		p11.eat();
		p2.teach();
		p22.eat();
	}
}