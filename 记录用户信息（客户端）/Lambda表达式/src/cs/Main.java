package cs;

public class Main {

    public static void main(String[] args){
        AN a = new AN();
        //Lambda表达式（该接口内方法的形参列表->代码块）
        /**
         * 没有返回值，重写接口内的方法
         * @param () taste方法的形参列表
         * @param Sy…… 重写taste的方法
         */
        a.eat(() -> System.out.println("Lambda表达式！"));
        /**
         * @param msg （接口中）fly方法的形参列表
         * @param Sy…… 重写fly方法
         * @param ret…… 返回值返回到AN类中
         */
        a.fly(msg -> {
            System.out.println("Lambda！");
            return "难受？！";});
        /**
         * 返回值（x+y）直接返回到AN类中
         */
        a.add((x, y) -> x+y);
    }

}
