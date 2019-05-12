public class CharThread implements Runnable {

    private Printer printer;
    public CharThread(Printer printer){
        this.printer = printer;
    }
    public void run(){
        for(int i=0;i<26;i++){
            printer.PrintChar((char)(i+65));
        }
    }
}
