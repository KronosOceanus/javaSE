package cs;

public class OutputFactory {
    //工厂用来创建Output类型的Printer实例（一般用静态工厂方法）
    public static Output getOutput()
    {
        return new Printer();
    }
}
