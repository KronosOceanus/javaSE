package cs;

public class Computer
{
    private Output out;
    public Computer(Output out)
    {
        this.out = out;
    }
    //通过接口Output调用该接口实现类Printer的方法
    public void keyIn(String msg)
    {
        out.getData(msg);
    }
    //模拟打印
    public void print()
    {
        out.out();
    }
}
