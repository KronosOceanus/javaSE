package cs;

public interface Output
{
	//接口中定义常量，普通方法，默认方法
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
        System.out.println("接口测试！");
    }
}
