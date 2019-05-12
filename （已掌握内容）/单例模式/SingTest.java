class SingLeton
{
	//����ʽ���̲߳���ȫ��
	//�趨�����������ǰ������SingLetonʵ��
	private static SingLeton instance;
	//��private����ʹ����޷������µ�SingLetonʵ��
	private SingLeton(){}
	//�������getter����
	public static SingLeton getInstance()
	{
		if(instance==null)
		{
			instance=new SingLeton();
		}
		return instance;
	}
	/**����ʽ���̰߳�ȫ������Ҫʱÿ�ζ�����һ���¶���
		private static SingLeton instance = new SingLeton();
		private SingLeton(){}
		//�������getter����
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
		//s1Ϊnull���½�SingLetonʵ������s1
		SingLeton s1=SingLeton.getInstance();
		//��SingLeton�е�static������ֵ��s2
		SingLeton s2=SingLeton.getInstance();
		//��ͬһ��ʵ��
		System.out.println(s1==s2);
	}
}