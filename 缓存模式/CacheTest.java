class CacheImmutale
{
	private static int MAX_SIZE=10;
	private static CacheImmutale[] cache=new CacheImmutale[MAX_SIZE];
	private static int pos=0;
	private String name;
	//隐藏构造器，使该类只能通过valueOf方法获取实例
	private CacheImmutale(String name)
	{
		this.name=name;
	}
	public String getName()
	{
		return this.name;
	}
	//缓存构造器，返回类型为CacheImmutale的方法
	public static CacheImmutale valueOf(String name)
	{
		for(int i=0;i<MAX_SIZE;i++)
		{
			//遍历缓存池，如果有相同的，从缓存池中直接调用并返回
			if(cache[i]!=null&&cache[i].getName().equals(name))
				return cache[i];
		}
		//如果缓存池满，把第一个元素覆盖，使起始位置为1
		if(pos==MAX_SIZE)
		{
			cache[0]=new CacheImmutale(name);
			pos=1;
		}
		//否则缓存池接着缓存该对象
		else
		{
			cache[pos++]=new CacheImmutale(name);
		}
		//如果缓存池中没有与传入东西相同的，返回新建的实例
		return cache[pos-1];
	}
	//重写equals方法
	public boolean equals(Object obj)
	{
		//是同一个对象
		if(this==obj)
			return true;
		//不为空且类别相同，判断该对象中的name实例是否相同
		if(obj!=null&&obj.getClass()==CacheImmutale.class)
		{
			CacheImmutale c=(CacheImmutale)obj;
			//递归
			return name.equals(c.getName());
		}
		return false;
	}
	public int hashCode()
	{
		return this.hashCode();
	}
}
public class CacheTest
{
	public static void main(String[] args)
	{
		//创建c1，一个CacheImmutale类的java字符串存到缓存池中
		CacheImmutale c1=CacheImmutale.valueOf("java");
		//创建c2，缓存池中已有字符串java，直接调用返回，所以是同一个实例
		CacheImmutale c2=CacheImmutale.valueOf("java");
		CacheImmutale c3=CacheImmutale.valueOf("java");
		System.out.println((c1==c2)+" "+(c2==c3));
	}
}