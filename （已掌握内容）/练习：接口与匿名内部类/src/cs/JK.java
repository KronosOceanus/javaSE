package cs;

public interface JK {
    void out();
    default void test()
    {
        System.out.println("接口测试！");
    }
}
