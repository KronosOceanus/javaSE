public class MyTest extends Thread {

    private Account account;
    public MyTest(Account account,String name){
        super(name);
        this.account = account;
    }
    public void run(){
        for(int i=0;i<10;i++){
            if(i == 6){
                //i == 6时候切换当前副本账户名为设定线程名称
                account.setName(getName());
            }
            //启动两个线程时候线程名为null
            System.out.println(account.getName() + "账户i的值: " + i);
        }
    }
}
