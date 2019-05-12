package cs;

//实现两个接口的类
public class Printer implements Output,Product
{
	/**
	 *@param printData 存放要打印数据的数组
	 *@param dataNum 记录打印的数据数量
	 */
    private String[] printData = new String[MAX];
    private int dataNum = 0;
	//打印
    public void out()
    {
		//要打印的数据不为0
        while (dataNum > 0)
		{
            System.out.println("打印机打印：" + printData[0]);
			//数组整体前移1位
            System.arraycopy(printData,1,printData,0,--dataNum);
        }
    }
	//得到并储存数据
    public void getData(String msg)
    {
        if(dataNum >= MAX) 
		{
            System.out.println("队列已满无法储存");
        }
        else{
            printData[dataNum++] = msg;
        }
    }
    public int getProduceTime()
    {
        return 45;
    }
}
