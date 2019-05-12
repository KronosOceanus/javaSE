package cs; //包名
import static java.lang.System.*; //导入System包中全部s变量
public class Gzqcz //方法器重载
{
	private String name;
	private int age;
	private int weight;
	public Gzqcz(String name,int age)
	{
		this.name=name;
		this.age=age;
	}
	public Gzqcz(String name,int age,int weight)
	{
		this(name,age); //构造器
		this.weight=weight;
	}
	public static void main(String[] args)
	{
		Gzqcz tc=new Gzqcz("蔡帅",19,57);
		out.println(""+tc.name+":"+tc.age+"周岁 "+tc.weight+"kg");
	}
}
