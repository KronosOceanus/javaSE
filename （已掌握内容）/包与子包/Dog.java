package cs;
import cs.css.*; //导入子包中所有类
import static java.lang.System.*;
public class Dog
{
	private void say(String s)
	{
		out.println(s);
		new D().run(); //创建实例引用子包中的方法
	}
	public static void main(String[] agrs)
	{
		Dog dog=new Dog();
		dog.say("md!");
	}
}//再javac -d . Dog.java编译包，最后java cs.Dog运行
