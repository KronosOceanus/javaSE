public class NameAge // ������
{
	private static class Person // �����ڲ���,privateֻ����NameAge���е�������������
	{
		public String name; //�����Ա����
		public int age;
		public void say(String content) // ���巽��
		{
			System.out.print(content);
		}
	}
	public static void main(String[] args) // ���е�������
	{
		Person p = new Person(); // ��������
		p.name = "��˧";
		p.age = 19; // �Զ���ֵ
		p.say("Hello"); // ���÷���
		System.out.println(" " + p.name);
		p.say("your age is");
		System.out.print(":" + p.age);
	}
}