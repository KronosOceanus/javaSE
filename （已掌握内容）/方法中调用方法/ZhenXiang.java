public class ZhenXiang
{
	private static class Person //�����ڲ���
    {
	    public String name; //�����Ա����
        public void say(String content)
	    {
		    System.out.print(content);
	    }
	    public void eat(String food)
	    {
		    System.out.print(food);
		    say("���㣡"); //eat�����е���say����(ʡ��this,������ͬ);��s�������ʷ�s����
	    }
    }
	public static void main(String[] args)
	{
	    Person p=new Person(); //�������
	    p.name="������";
		System.out.print(p.name+":");
		p.eat("����"); //ʵ������s��ķ�s����
	}
}
