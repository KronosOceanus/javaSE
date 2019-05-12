package cs;

public class Main {

    public static void main(String[] args)
    {
        //汽车
        Vehicle c = new Car("汽车",6);
        System.out.println(c.toString());
        //卡车
        Vehicle t = new Truck("卡车",40);
        System.out.println(t.toString());
    }
}
