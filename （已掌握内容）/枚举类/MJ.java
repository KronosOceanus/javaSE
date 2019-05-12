enum Season
{
	SPRING,SUMMER,AUTUMM,WINTER;
	//应该用private修饰
	public String name;
	/**
	 *并使用下面的setter方法，再自行建立getter方法
	public void setName()
	{
		switch(this)
		{
			case SPRING:
			if(name.equals("春天！"))
				this.name = name;
			else 
			{
				System.out.println("参数错误！");
				return;
			}
			case SUMMER:
			……
			……
		}
	}
	*/
}
public class MJ
{
	public static void main(String[] args)
	{
		//枚举类型实例只能是枚举值
		Season s = Enum.valueOf(Season.class,"Summer");
		s.name = "夏天！";
		//但是枚举类型打印出来是枚举变量名
		System.out.println(s+"代表"+s.name);
	}
}