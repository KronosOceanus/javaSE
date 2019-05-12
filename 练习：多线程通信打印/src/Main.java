public class Main {

    public static void main(String[] args) {

        //为了减少创建class，可用Lambda表达式创建Runnable对象
        Printer pt = new Printer();
        new Thread(new IntThread(pt)).start();
        new Thread(new CharThread(pt)).start();
    }
}
