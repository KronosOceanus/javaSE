public class ZhenXiang
{
	private static class Person //定义内部类
    {
	    public String name; //定义成员变量
        public void say(String content)
	    {
		    System.out.print(content);
	    }
	    public void eat(String food)
	    {
		    System.out.print(food);
		    say("真香！"); //eat方法中调用say方法(省略this,对象相同);非s方法访问非s方法
	    }
    }
	public static void main(String[] args)
	{
	    Person p=new Person(); //构造变量
	    p.name="王境泽";
		System.out.print(p.name+":");
		p.eat("炒饭"); //实例访问s类的非s方法
	}
}
