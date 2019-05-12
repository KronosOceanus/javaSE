import java.util.*;

class R
{
    int count;
    public R(int count)
    {
        this.count = count;
    }
    public String toString()
    {
        return "R[count"+count+"]";
    }
    //重写equals方法
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj != null && obj.getClass() == R.class)
        {
            R r = (R)obj;
            return r.count == this.count;
        }
        return false;
    }
    //相应的重写hashcode方法
    public int hashcode()
    {
        return this.count;
    }
}

public class Main {
    public static void main(String[] args)
    {
        HashSet hs = new HashSet();
        hs.add(new R(5));
        hs.add(new R(-3));
        hs.add(new R(9));
        hs.add(new R(-2));
        System.out.println(hs);
        //迭代器
        Iterator it = hs.iterator();
        //取出第一个元素（count为-2）并改变它的count为-3
        R first = (R)it.next();
        first.count = -3;
        //此时集合中有两个相同的R对象（count都为-3）
        System.out.println(hs);
        hs.remove(new R(-3));
        //删除操作失败
        System.out.println(hs);
        //两个R对象的count为-3
        System.out.println("是否包含-3？："+hs.contains(new R(-3)));
        //count为-2的R对象被覆盖
        System.out.println("是否包含-2？："+hs.contains(new R(-2)));
    }
}
