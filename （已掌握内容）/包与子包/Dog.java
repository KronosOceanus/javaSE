package cs;
import cs.css.*; //�����Ӱ���������
import static java.lang.System.*;
public class Dog
{
	private void say(String s)
	{
		out.println(s);
		new D().run(); //����ʵ�������Ӱ��еķ���
	}
	public static void main(String[] agrs)
	{
		Dog dog=new Dog();
		dog.say("md!");
	}
}//��javac -d . Dog.java����������java cs.Dog����
