package cs;

public abstract class SpeedMeter
{
	/**
	 *@param turnRate ����ת��
	 */
	private double turnRate;
	//�õ��������Ӱ뾶
	public abstract double getR();
	public void setTurnRate(double turnRate)
	{
		this.turnRate=turnRate;
	}
	public double getTurnRate()
	{
		return this.turnRate;
	}
	//���󷽷�ʵ�ֺ����ʵ����ͨ����
	public double getSpeed()
	{
		return java.lang.Math.PI*2*getR()*turnRate;
	}
}