package cs;

public interface Output
{
	//接口中只能定义常量
	int M=50;
	//普通方法
	void out();
	void getData(String msg);
	//default修饰默认方法
	default void test()
	{
		System.out.println("接口中的默认方法！");
	}
	//接口中的类方法
	static String staticTest()
	{
		return "接口中的s方法！";
	}
}