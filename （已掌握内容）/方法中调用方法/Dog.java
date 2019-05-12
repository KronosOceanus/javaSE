public class Dog
{
	private void jump()
	{
		System.out.println("jumping");
	}
	private void run()
	{
		System.out.println("running");
		jump(); //对象相同，省略this
    }
    public static void main(String[] args)
	{
		Dog dog= new Dog(); //构造实例
	    dog.run(); //引用方法
		System.out.println("yes!");
	}
}