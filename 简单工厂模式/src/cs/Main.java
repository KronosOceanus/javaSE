package cs;

public class Main {

    public static void main(String[] args)
    {
        //创建一个电脑带有OutPut接口
        Computer c = new Computer(OutputFactory.getOutput());
        c.keyIn("java");
        c.keyIn("i love you!");
        c.print();
    }
}

