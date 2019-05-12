package cs;

public class Print implements Command
{
    public void process(int[] target)
    {
        for (int imp:target)
        {
             System.out.println(imp);
        }
    }
}
