package cs;

public class Printer implements Output
{
    private String[] printData = new String[MAX];
    private int dataNum = 0;
    public void out()
    {
        while (dataNum > 0) {
            System.out.println("打印机打印：" + printData[0]);
            System.arraycopy(printData,1,printData,0,--dataNum);
        }
    }
    public void getData(String msg)
    {
        if (dataNum >= MAX) {
            System.out.println("队列已满无法储存");
        } else {
            printData[dataNum++] = msg;
        }
    }
}
