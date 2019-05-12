package cs;

public class Car extends Vehicle{
    //继承父类变量
    public Car(String name,int capacity)
    {
        this.name = name;
        this.capacity = capacity;
    }
    //实现两个抽象方法
    public double getRadius()
    {
        return 0.25;
    }
    public void drive()
    {
        System.out.println(this.name+"正在公路上行驶！");
    }
    //重写toString方法
    public String toString()
    {
        setTurnRate(50);
        drive();
        return ("载客量为"+this.capacity+"人！\n速度为"+getSpeed()+"m/s！");
    }
}
