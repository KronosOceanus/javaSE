package cs;

class Name
{
    private String firstName;
    private String lastName;
    public Name(){};
    public Name(String firstName,String lastName)
    {
        this.firstName=firstName;
        this.lastName=lastName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
public class PersonTrue
{
 	private final Name name;
	public PersonTrue(Name name)
 	{
		//�½�Name��ʵ������������name��ֻ����һ�Σ������ݸ���this.name
		this.name=new Name(name.getFirstName(),name.getLastName());
 	}
	public Name getName()
	{
		//����ֵ�Ǵ��빹�����е�name��һ�����죬name���䣬����name.getterҲ����
		//�½�Nameʵ������ʵ���빹������ʵ��������ͬ
		return new Name(name.getFirstName(),name.getLastName());
	}
	public static void main(String[] args)
	{
		Name n=new Name("˧","��");
		PersonTrue p=new PersonTrue(n);
		System.out.println(p.getName().getFirstName());
		//�޸Ŀɱ���Nameʵ��n����getName����ֵ���ǵ�һ�δ����name
		n.setFirstName("��");
		System.out.println(p.getName().getFirstName());
	}
}