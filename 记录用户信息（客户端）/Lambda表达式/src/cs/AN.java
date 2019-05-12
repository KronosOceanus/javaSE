package cs;

public class AN {
    //(执行第一步)新建接口类型对象，通过接口调用该类中的方法(具体方法在主函数Lambda表达式中)
    //必须在方法中执行接口中的方法！！！！！
    public void eat(Eatable e){
        e.taste();
    }
    public void fly(Flyable f) {
        //参数（你飞？！）传给Lambda表达式中的形参
        System.out.println(f.fly("你飞？！"));
    }
    public void add(Addable a){
        System.out.println("5 + 8 = "+a.add(5,8));
    }
}
