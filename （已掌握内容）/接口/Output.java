package cs;

public interface Output
{
	//�ӿ���ֻ�ܶ��峣��
	int M=50;
	//��ͨ����
	void out();
	void getData(String msg);
	//default����Ĭ�Ϸ���
	default void test()
	{
		System.out.println("�ӿ��е�Ĭ�Ϸ�����");
	}
	//�ӿ��е��෽��
	static String staticTest()
	{
		return "�ӿ��е�s������";
	}
}