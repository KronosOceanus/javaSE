enum Season
{
	SPRING,SUMMER,AUTUMM,WINTER;
	//Ӧ����private����
	public String name;
	/**
	 *��ʹ�������setter�����������н���getter����
	public void setName()
	{
		switch(this)
		{
			case SPRING:
			if(name.equals("���죡"))
				this.name = name;
			else 
			{
				System.out.println("��������");
				return;
			}
			case SUMMER:
			����
			����
		}
	}
	*/
}
public class MJ
{
	public static void main(String[] args)
	{
		//ö������ʵ��ֻ����ö��ֵ
		Season s = Enum.valueOf(Season.class,"Summer");
		s.name = "���죡";
		//����ö�����ʹ�ӡ������ö�ٱ�����
		System.out.println(s+"����"+s.name);
	}
}