package cs;

public class ProcessArray
{
    /**
     * 需要处理的数组类，因为处理时无法确定行为，所以传入Command参数负责对数组的处理行为
     * @param target 被处理数组
     * @param cmd 处理方法（接口实例/相当于传入函数）
     */
    public void process(int[] target,Command cmd)
    {
        cmd.process(target);
    }
}
