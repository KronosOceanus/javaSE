package cs;

public abstract class SpeedMeter
{
	/**
	 *@param turnRate 轮子转速
	 */
	private double turnRate;
	//得到子类轮子半径
	public abstract double getR();
	public void setTurnRate(double turnRate)
	{
		this.turnRate=turnRate;
	}
	public double getTurnRate()
	{
		return this.turnRate;
	}
	//抽象方法实现后才能实行普通方法
	public double getSpeed()
	{
		return java.lang.Math.PI*2*getR()*turnRate;
	}
}