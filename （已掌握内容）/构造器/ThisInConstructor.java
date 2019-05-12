public class ThisInConstructor
{
	int foo;
	public ThisInConstructor() //构造器必须与类同名，且无任何返回值
	{
		int foo=0;
		this.foo=6; //构造器中this代表正在初始化的对象，然后赋值
	}
	public static void main(String[] args)
	{
		System.out.println(new ThisInConstructor().foo);//使用ThisInConstructor创建对象的foo都=6
	}
}