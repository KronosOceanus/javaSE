class SingLeton
{
	//懒汉式（线程不安全）
	//设定类变量储存以前创建的SingLeton实例
	private static SingLeton instance;
	//用private修饰使外界无法创建新的SingLeton实例
	private SingLeton(){}
	//类变量的getter方法
	public static SingLeton getInstance()
	{
		if(instance==null)
		{
			instance=new SingLeton();
		}
		return instance;
	}
	/**饿汉式（线程安全），需要时每次都创建一个新对象
		private static SingLeton instance = new SingLeton();
		private SingLeton(){}
		//类变量的getter方法
		public static SingLeton getInstance()
		{
			return instance;
		}
	*/
}
public class SingTest
{
	public static void main(String[] args)
	{
		//s1为null，新建SingLeton实例赋给s1
		SingLeton s1=SingLeton.getInstance();
		//将SingLeton中的static变量赋值给s2
		SingLeton s2=SingLeton.getInstance();
		//是同一个实例
		System.out.println(s1==s2);
	}
}