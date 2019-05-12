package cs;

public abstract class Vehicle
{
    //车具有的属性用protected修饰便于子类改写(不需要setter和getter方法)
    protected String name;
    protected int capacity;
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    //turnRate要在抽象类普通方法中实现，所以不用getter方法
    private double turnRate;
    public Vehicle(){}
    //两个抽象方法
    public abstract double getRadius();
    public abstract void drive();
    //得到转速与速度
    public void setTurnRate(double turnRate)
    {
        this.turnRate = turnRate;
    }
    public double getSpeed()
    {
        return java.lang.Math.PI*2*getRadius()*turnRate;
    }
}
