package cs;

//ʵ�������ӿڵ���
public class Printer implements Output,Product
{
	/**
	 *@param printData ���Ҫ��ӡ���ݵ�����
	 *@param dataNum ��¼��ӡ����������
	 */
    private String[] printData = new String[MAX];
    private int dataNum = 0;
	//��ӡ
    public void out()
    {
		//Ҫ��ӡ�����ݲ�Ϊ0
        while (dataNum > 0)
		{
            System.out.println("��ӡ����ӡ��" + printData[0]);
			//��������ǰ��1λ
            System.arraycopy(printData,1,printData,0,--dataNum);
        }
    }
	//�õ�����������
    public void getData(String msg)
    {
        if(dataNum >= MAX) 
		{
            System.out.println("���������޷�����");
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
