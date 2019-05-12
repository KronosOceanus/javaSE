class CacheImmutale
{
	private static int MAX_SIZE=10;
	private static CacheImmutale[] cache=new CacheImmutale[MAX_SIZE];
	private static int pos=0;
	private String name;
	//���ع�������ʹ����ֻ��ͨ��valueOf������ȡʵ��
	private CacheImmutale(String name)
	{
		this.name=name;
	}
	public String getName()
	{
		return this.name;
	}
	//���湹��������������ΪCacheImmutale�ķ���
	public static CacheImmutale valueOf(String name)
	{
		for(int i=0;i<MAX_SIZE;i++)
		{
			//��������أ��������ͬ�ģ��ӻ������ֱ�ӵ��ò�����
			if(cache[i]!=null&&cache[i].getName().equals(name))
				return cache[i];
		}
		//�������������ѵ�һ��Ԫ�ظ��ǣ�ʹ��ʼλ��Ϊ1
		if(pos==MAX_SIZE)
		{
			cache[0]=new CacheImmutale(name);
			pos=1;
		}
		//���򻺴�ؽ��Ż���ö���
		else
		{
			cache[pos++]=new CacheImmutale(name);
		}
		//����������û���봫�붫����ͬ�ģ������½���ʵ��
		return cache[pos-1];
	}
	//��дequals����
	public boolean equals(Object obj)
	{
		//��ͬһ������
		if(this==obj)
			return true;
		//��Ϊ���������ͬ���жϸö����е�nameʵ���Ƿ���ͬ
		if(obj!=null&&obj.getClass()==CacheImmutale.class)
		{
			CacheImmutale c=(CacheImmutale)obj;
			//�ݹ�
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
		//����c1��һ��CacheImmutale���java�ַ����浽�������
		CacheImmutale c1=CacheImmutale.valueOf("java");
		//����c2��������������ַ���java��ֱ�ӵ��÷��أ�������ͬһ��ʵ��
		CacheImmutale c2=CacheImmutale.valueOf("java");
		CacheImmutale c3=CacheImmutale.valueOf("java");
		System.out.println((c1==c2)+" "+(c2==c3));
	}
}