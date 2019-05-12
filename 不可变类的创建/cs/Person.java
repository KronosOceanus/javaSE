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
//���ɱ��ഴ��ʧ��
public class Person
{
	//name�����ñ���
	private final Name name;
	public Person(Name name)
	{
		this.name=name;
	}
	public Name getName() {
		return name;
	}
	public static void main(String[] args)
	{
		Name n=new Name("˧","��");
		Person p=new Person(n);
		System.out.println(p.getName().getFirstName());
		//�����޸�this.name�������޸��˿ɱ���Name��ʵ��n
		n.setFirstName("��");
		//�����޸ĺ��FirstName
		System.out.println(p.getName().getFirstName());
	}
}
//�봴�����ɱ��࣬��Ҫ���������ñ�����������ĳɱ����е�PersonTrue.java