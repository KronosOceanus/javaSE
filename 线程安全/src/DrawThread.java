//取钱线程
public class DrawThread extends Thread {
    //用户账户
    private Account account;
    //当前线程希望取钱数
    private double drawAmount;
    public DrawThread(String name,Account account,double drawAmount){
        super(name);
        this.account = account;
        this.drawAmount = drawAmount;
    }
    //多个线程共同修改一个共享数据时，将涉及数据安全问题
    //同步方法写法
    public void run(){
        account.draw(drawAmount);
    }

    /**
        //同步代码块写法
         public void run(){

            //先锁定目前的account，其他线程就无法修改它
            synchronized(account) {

                //账户余额大于取钱数目
                if (account.getBalance() >= drawAmount) {
                    System.out.println("取钱成功: " + drawAmount);
                    /**
                     * //通过让线程睡眠1ms来暂时阻止此时切换线程
                     * try{
                     *      Thread.sleep(1);
                     *      }
                     * catch(InterruptedException ex){
                     *      ex.printStackTrace();
                     *      }
                     *
                    account.setBalance(account.getBalance() - drawAmount);
                    System.out.println("\t余额为: " + account.getBalance());
                }
                else {
                    System.out.println("取钱失败，余额不足！");
                }
            }
        }
     */
}
