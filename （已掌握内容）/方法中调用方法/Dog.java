public class Dog
{
	private void jump()
	{
		System.out.println("jumping");
	}
	private void run()
	{
		System.out.println("running");
		jump(); //������ͬ��ʡ��this
    }
    public static void main(String[] args)
	{
		Dog dog= new Dog(); //����ʵ��
	    dog.run(); //���÷���
		System.out.println("yes!");
	}
}