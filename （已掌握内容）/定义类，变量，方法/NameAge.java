public class NameAge // 定义类
{
	private static class Person // 定义内部类,private只可由NameAge类中的其他方法调用
	{
		public String name; //定义成员变量
		public int age;
		public void say(String content) // 定义方法
		{
			System.out.print(content);
		}
	}
	public static void main(String[] args) // 类中的主函数
	{
		Person p = new Person(); // 创建对象
		p.name = "蔡帅";
		p.age = 19; // 对对象赋值
		p.say("Hello"); // 调用方法
		System.out.println(" " + p.name);
		p.say("your age is");
		System.out.print(":" + p.age);
	}
}