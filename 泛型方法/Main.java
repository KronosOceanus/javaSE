import java.util.*;

public class Main
{
    //将集合转换为父类集合
    public static<T> void copyTo(Collection<? extends T> a,Collection<T> b)
    {
        b.addAll(a);
        a.removeAll(a);
    }
    public static void main(String[] args)
    {
        Set<Integer> c =  new HashSet<>();
        for(int i=0;i<10;i++)
            c.add(i);
        Collection<Object> d = new ArrayList<>();
        Main.copyTo(c,d);
        System.out.println(c);
        System.out.println(d);
    }
}