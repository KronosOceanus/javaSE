class Bird
{
	public void fly()
	{
		System.out.println("I am flying!");
	}
}
public class Tuoniao extends Bird
{
	public void fly()
	{
		System.out.println("I can't fly!I just could run!");
	}
	public static void main(String[] args)
	{
		Tuoniao t=new Tuoniao();
		t.fly();
	}
}