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
		//新建Name类实例，并将传入name（只传入一次）的内容赋给this.name
		this.name=new Name(name.getFirstName(),name.getLastName());
 	}
	public Name getName()
	{
		//返回值是传入构造器中的name，一旦构造，name不变，所以name.getter也不变
		//新建Name实例，该实例与构造器中实例内容相同
		return new Name(name.getFirstName(),name.getLastName());
	}
	public static void main(String[] args)
	{
		Name n=new Name("帅","蔡");
		PersonTrue p=new PersonTrue(n);
		System.out.println(p.getName().getFirstName());
		//修改可变类Name实例n，但getName返回值还是第一次传入的name
		n.setFirstName("丑");
		System.out.println(p.getName().getFirstName());
	}
}