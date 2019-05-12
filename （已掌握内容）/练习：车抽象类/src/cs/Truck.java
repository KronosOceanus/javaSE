package cs;

public class Truck extends Vehicle {
    public Truck(String name,int capacity)
    {
        this.name = name;
        this.capacity = capacity;
    }
    public double getRadius()
    {
        return 0.40;
    }
    public void drive()
    {
        System.out.println(this.name+"正在运输道上行驶！");
    }
    public String toString()
    {
        setTurnRate(25);
        drive();
        return ("载重为"+this.capacity+"吨！\n速度为"+getSpeed()+"m/s！");
    }
}