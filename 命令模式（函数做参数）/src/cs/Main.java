package cs;

public class Main {

    public static void main(String[] args)
    {
        ProcessArray pa = new ProcessArray();
        int[] a = {7,8,2,3};
        pa.process(a,new Print());
        System.out.println("---------------");
        pa.process(a,new Add());
    }
}
