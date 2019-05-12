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
//不可变类创建失败
public class Person
{
	//name是引用变量
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
		Name n=new Name("帅","蔡");
		Person p=new Person(n);
		System.out.println(p.getName().getFirstName());
		//不可修改this.name，但是修改了可变类Name的实例n
		n.setFirstName("丑");
		//访问修改后的FirstName
		System.out.println(p.getName().getFirstName());
	}
}
//想创建不可变类，就要保护好引用变量，将程序改成本包中的PersonTrue.java