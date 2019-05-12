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
    //��дequals����
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
    //��Ӧ����дhashcode����
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
        //������
        Iterator it = hs.iterator();
        //ȡ����һ��Ԫ�أ�countΪ-2�����ı�����countΪ-3
        R first = (R)it.next();
        first.count = -3;
        //��ʱ��������������ͬ��R����count��Ϊ-3��
        System.out.println(hs);
        hs.remove(new R(-3));
        //ɾ������ʧ��
        System.out.println(hs);
        //����R�����countΪ-3
        System.out.println("�Ƿ����-3����"+hs.contains(new R(-3)));
        //countΪ-2��R���󱻸���
        System.out.println("�Ƿ����-2����"+hs.contains(new R(-2)));
    }
}
