import java.util.*;

class Err
{
    int count;
    public Err(int count)
    {
        this.count = count;
    }
    public String toString()
    {
        return "Err[count"+count+"]";
    }
}
public class Main {
    public static void main(String[] args)
    {
        //定制排序（根据count大小降序排列）
        //lambda表达式直接在TreeSet集合构造器中重写comparator接口的int compare(o1,o2)方法
        TreeSet ts = new TreeSet((o1,o2) -> ((Err)o2).count > ((Err)o1).count?1:
                ((Err)o2).count == ((Err)o1).count?0:-1);
        ts.add(new Err(8));
        ts.add(new Err(7));
        ts.add(new Err(20));
        ts.add(new Err(-5));
        System.out.println(ts);
    }
}