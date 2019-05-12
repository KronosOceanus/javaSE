package cs;

public class Main {
    public static void main(String[] args){
        AnonymousInner a = new AnonymousInner();
        a.sc(new JK()
        {
            public void out()
            {
                //匿名内部类实现接口内默认方法
                test();
                System.out.println("匿名内部类实现接口内普通方法！");
            }
        });
    }
}
