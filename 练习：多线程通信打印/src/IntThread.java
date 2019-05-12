public class IntThread implements Runnable {

    private Printer printer;
    public IntThread(Printer printer){
        this.printer = printer;
    }
    public void run(){
        for(int i=0;i<26;i++){
            printer.PrintInt(2*i+1,2*i+2);
        }
    }
}
