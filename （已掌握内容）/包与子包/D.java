package cs.css; //子包名
public class D
{
    public void run()
	{
	    System.out.println("running!");
	}
}//先javac -d . D.java编译子包，同包（不包括子包）可以任意访问