package cs;

public class Main {

    public static void main(String[] args)
    {
        //创建一个带有Output接口（即Printer实例）的电脑
        Computer c = new Computer(OutputFactory.getOutput());
        c.keyIn("java");
        c.keyIn("i love you!");
        c.print();
    }
}

