package cs;

public class Add implements Command
{
    public void process(int[] target)
    {
        int sum = 0;
        for(int imp:target)
        {
            sum += imp;
        }
        System.out.println("数组之和为:"+sum);
    }
}
