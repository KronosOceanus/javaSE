public class DrawThread extends Thread{
    private Account account;
    private double drawAmount;
    public DrawThread(String name,Account account,double drawAmount){
        super(name);
        this.account = account;
        this.drawAmount = drawAmount;
    }

    //重复4次取钱
    public void run(){
        for(int i=0;i<4;i++){
            account.draw(drawAmount);
        }
    }
}
