package cs;

public interface Output
{
	//�ӿ��ж��峣������ͨ������Ĭ�Ϸ���
    int MAX=50;
    void getData(String msg);
    void out();
    default void print(String...msgs)
    {
        for(String msg:msgs)
        {
            System.out.println(msg);
        }
    }
    default void test()
    {
        System.out.println("�ӿڲ��ԣ�");
    }
}
